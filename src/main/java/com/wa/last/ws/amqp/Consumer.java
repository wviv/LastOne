package com.wa.last.ws.amqp;

import com.alibaba.fastjson.JSON;
import com.wa.last.ws.MqData;
import com.wa.last.ws.netty.ChannelHandlerPool;
import com.wa.last.ws.redis.RedisUtil;
import com.rabbitmq.client.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Configuration
public class Consumer {

    @Value("${ws.mq-queue-name}")
    String queueName ;

    @Value("${ws.mq-exchange-name}")
    String exchangeName;

    @Resource
    StringRedisTemplate redisTemplate;

    /**
     * 队列名  取hostname ,如果取不到hostname，就用appname + 随机数
     * 独占队列 创建者断开之后 删除队列
     *
     * @return
     */
    @Bean(name = "ws-netty-queue")
    public Queue queue() {
        return new Queue(queueName, true, false, false);
    }

    @Bean(name = "ws-netty-binding")
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange());
    }

    @Bean(name = "ws-netty-exchange")
    public FanoutExchange exchange() {
        return new FanoutExchange(exchangeName, true, false);
    }


    @RabbitListener(queues = "${ws.mq-queue-name}", ackMode = "MANUAL")
    public void helloLister2(Channel channel, Message message, @Payload Object payload, @Headers Object headers) throws IOException {

        //判断本地是否有Channel，有的ack  没有的nack 向下传递
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        //缓存id
        String correlationId = message.getMessageProperties().getCorrelationId();

        byte[] body = message.getBody();

        String jsonBody = new String(body);

        MqData mqData = JSON.parseObject(jsonBody, MqData.class);

        //TODO  用户唯一标识
        String user = mqData.getUserId();

        //body
        String msg = new String(message.getBody());


        String stateKey = RedisUtil.stateKey(user, correlationId);
        String stateVal = redisTemplate.opsForValue().get(RedisUtil.stateKey(user, correlationId));
        int state = StringUtils.isBlank(stateVal) ? 0 : Integer.parseInt(stateVal);

        io.netty.channel.Channel nettyChannel = ChannelHandlerPool.channel(user);

        if (Objects.nonNull(nettyChannel) && state <= 10) {

            nettyChannel.writeAndFlush(new TextWebSocketFrame(msg));

            channel.basicAck(deliveryTag, false);
            log.info("消息已送达 {}",stateKey);
            //TODO 删除Redis 相关数据
            redisTemplate.delete(RedisUtil.stateKey(user, correlationId));
            redisTemplate.delete(RedisUtil.dataKey(user, correlationId));

        } else if (state <= 10) {

            //向下传递  可以把队列的名字都用一样的，并进行持久化，
            channel.basicNack(deliveryTag, false, true);
            redisTemplate.opsForValue().set(stateKey, ++state + "");
            log.error("没有找到netty通道，放回队列重试 redisKey {}",stateKey);

        } else {
            //TODO   超出时间缓存数据到redis，把消息确认掉，然后新增接口可以手动处理或者手动推送或者当当当当。。。
            channel.basicAck(deliveryTag, false);
            log.error("超出重试次数 确认掉消息 redisKey {}", stateKey);
        }
    }
}
