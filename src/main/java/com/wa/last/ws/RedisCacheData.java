package com.wa.last.ws;


import lombok.Data;

/**
 * 缓存数据
 * 记录进程以及mq的高可用
 *
 */
@Data
public class RedisCacheData {

    /**
     *  userid  + mqid  + 。。。
     */
    String user;

    //TODO 是不是可以不保存
    /**
     * 推送数据
     */
    String data;

    /**
     * 1 ready 生产者发送
     * 2 send  发送成功
     * 5 ack   消息ack 之后的都可以删除
     * 8 success 删除
     * 0 失败
     */
    Integer status;

    /**
     * 消费失败次数，到达一定次数之后删除
     */
    Integer failNum;

}
