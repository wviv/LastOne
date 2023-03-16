package com.wa.last.ws.netty;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import org.apache.commons.lang.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 保存所有通道
 */
public class ChannelHandlerPool {

    /**
     * 每个登录账号+主机 在本地缓存中 对应的唯一id
     */
    public static String key = "id";

    /**
     * channel key
     */
    public static AttributeKey<String> attributeKey = AttributeKey.valueOf(key);

    /**
     * 本地
     */
    public static Set<Channel> channelGroup = Collections.synchronizedSet(new HashSet<>());


    /**
     * 根据id 获取通道
     * @param id
     * @return
     */
    public static Channel channel(String id){

        for (Channel channel : channelGroup) {
            String value = channel.attr(attributeKey).get();

            //查到一个即返回
            if (StringUtils.equals(id, value)) {
                return channel;
            }
        }
        return null;
    }
}

