package com.wa.last.ws.amqp;


import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.wa.last.ws.MqData;
import com.wa.last.ws.redis.RedisUtil;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class Producer {

    @Value("${ws.mq-exchange-name}")
    String exchangeName;

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    StringRedisTemplate redisTemplate;


    public void send(MqData data) throws IOException {

        //TODO  改成  用户唯一标识
        String userId = data.getUserId();
        //mq 内部流传 以及redis 存放，区别一个医生多条消息
        String correlationId = IdUtil.simpleUUID();
        rabbitTemplate.convertAndSend(exchangeName, "", JSON.toJSONString(data), new CorrelationData(correlationId));


        //TODO 缓存一份数据一份状态
        //TODO 过期时间 根据业务实际情况做调整
        String stateKey = RedisUtil.stateKey(userId, correlationId);
        String dataKey = RedisUtil.dataKey(userId, correlationId);

        redisTemplate.opsForValue().set(stateKey, "1");
        redisTemplate.opsForValue().set(dataKey, JSON.toJSONString(data));
        redisTemplate.expire(stateKey, 10000, TimeUnit.SECONDS);
        redisTemplate.expire(dataKey, 10000, TimeUnit.SECONDS);

//        发送接收
//        rabbitTemplate.convertSendAndReceive();
    }
}
