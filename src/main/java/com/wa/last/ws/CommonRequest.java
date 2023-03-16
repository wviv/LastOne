package com.wa.last.ws;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonRequest {

    /**
     * 请求类型
     * 1：群聊消息
     * 2：私聊消息
     * 3：浏览器自己请求资源
     */
    private Integer type;

    /**
     * 私聊消息发送的目标
     */
    private String target;

    /**
     * 消息
     */
    private String message;

}
