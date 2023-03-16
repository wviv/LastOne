package com.wa.last.ws.amqp;

import com.wa.last.ws.MqData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@Api(tags = "消息队列")
@Slf4j
@RestController
@RequestMapping("/amqp")
@RequiredArgsConstructor()
public class AmqpController {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Resource
    Producer producer;

    @Resource
    VirtualHostConfiguration virtualHostConfiguration;

    @Resource
    StringRedisTemplate redisTemplate;


    @GetMapping("/send/{id}")
    @ApiOperation("send")
    public void info(@PathVariable String id,String msg) throws IOException {
        MqData mqData = new MqData();
        mqData.setUserId(id);
        mqData.setData(msg);

        //TODO  匹配规则引擎之后 触发

        producer.send(mqData);
    }

    @GetMapping("/virtualHost/{hostName}")
    @ApiOperation("新建虚拟主机")
    public String createVirtualHost(@PathVariable String hostName) {
        return virtualHostConfiguration.createVirtualHosts(hostName);
    }

    /**
     * 删除虚拟主机
     *
     * 先不开放权限
     * @param hostName
     * @return
     */
    @DeleteMapping("/virtualHost/{hostName}")
    @ApiOperation("删除虚拟主机")
    public String deleteVirtualHost(@PathVariable String hostName) {

        redisTemplate.opsForValue().set("dddd:message:bbbb",hostName);

        return "error";
//        if (StringUtils.isBlank(hostName) || StringUtils.equalsIgnoreCase("/",hostName)){
//            return "error";
//        }
//        return virtualHostConfiguration.deleteVirtualHosts(hostName);
    }

}
