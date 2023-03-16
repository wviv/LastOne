package com.wa.last.ws.redis;

public class RedisUtil {


    public static String stateKey(String userId, String correlationId) {
        return key(userId, correlationId, "state");
    }

    public static String dataKey(String userId, String correlationId) {
        return key(userId, correlationId, "data");
    }

    private static String key(String userId, String correlationId, String suffix) {
        return "dddd:message:" + userId + "-" + correlationId + ":" + suffix;
    }


}
