package com.wa.last.w1;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.UTF8Decoder;
import org.apache.commons.beanutils.BeanUtils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

public class W1 {
    public static void main(String[] args) throws UnsupportedEncodingException, InvocationTargetException, IllegalAccessException {
        String a = "%7B%22searchName%22%3A%22%E7%97%85%E7%90%86%E6%B4%BB%E6%A3%80%EF%BC%88%E5%B1%80%E9%83%A8%E5%88%87%E9%99%A4%E7%BB%84%E7%BB%87%2B%E7%97%85%E7%90%86%E5%A4%A7%E4%BD%93%E6%A0%87%E6%9C%AC%E6%91%84%E5%BD%B1%EF%BC%89%22%2C%22releaseCondition%22%3A1%2C%22size%22%3A10%2C%22start%22%3A1%2C%22status%22%3A1%2C%22tenantId%22%3A%22ed09abdb046c1a1814d33d90d82813c2%22%2C%22appKey%22%3A%22ED09ABDB046C1A185EBD16ADA4BB46009566B4B42115E4B5%22%2C%22staticKnowledgeType%22%3A%5B%22jcxm%22%5D%7D";
        String encode = URLDecoder.decode(a, "utf-8");

        Map<String,Object> map = JSON.parseObject(encode, Map.class);


        Ww1 ww1 = new Ww1();
        BeanUtils.populate(ww1,map);
        System.out.println(1);
    }
}
