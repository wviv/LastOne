package com.wa.last.ws.amqp;

import lombok.Data;

import java.util.Map;


/**
 * mq api 虚拟主机接口返回值
 */
@Data
public class VirtualHostInfo {

    String name;
    String tracing;
    Map<String, Object> cluster_state;
    Map<String, Object> messages_details;
    Map<String, Object> messages_ready_details;
    Map<String, Object> messages_unacknowledged_details;
    Map<String, Object> recv_oct_details;
    Map<String, Object> send_oct_details;
}
