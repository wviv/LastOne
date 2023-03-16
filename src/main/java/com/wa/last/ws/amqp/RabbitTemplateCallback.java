package com.wa.last.ws.amqp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * 消息发送回调
 */
@Slf4j
@Configuration
public class RabbitTemplateCallback  implements InitializingBean {

    @Resource
    RabbitTemplate rabbitTemplate;


    @Override
    public void afterPropertiesSet() throws Exception {

        //没有到达exchange时触发
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {

            //没找到对应exchange时为false
            if (!ack){
                log.error("消息发送失败 ，没有找到交换机: {} ",correlationData.getId());
//                stringRedisTemplate.opsForValue().get("key");
//                stringRedisTemplate.expire("key", Duration.ofDays(30));
            }else {
                //TODO  更新数据流状态，好像不太行，这个是全局的，
                // 如果可以改为 rabbitTemplate.convertAndSend() 时，单独监听，就可
                log.trace("消息到达交换机 : {}", correlationData.getId());
            }
        });

        //消息无法路由到queue时候触发。
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {

            log.error("消息路由失败  message : {} ", new String(message.getBody()));

            //TODO  在这里处理路由失败消息


        });


        /**
         * 设置全局correlationId
         */
        rabbitTemplate.setCorrelationDataPostProcessor((message , correlationData) -> {
            message.getMessageProperties().setCorrelationId(correlationData.getId());
            return correlationData;
        });
    }

}
