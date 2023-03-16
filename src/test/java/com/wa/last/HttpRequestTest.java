package com.wa.last;

import cn.hutool.http.HttpRequest;
import org.junit.Test;

import java.util.*;

public class HttpRequestTest {


    @Test
    public void t1(){

        HttpRequest post = HttpRequest.post("http://localhost:8082/drools/rule2");
        Map<String, Object> map = new HashMap<>();

        List<String> list = new ArrayList<>();
        list.add("大娃");
        list.add("二娃");
        list.add("三娃,四娃,五娃,六娃");

//        map.put("aaa", list);
        map.put("aaa", new String[]{"数组1","数组2","数组3,数组4,数组5,数组6"});
        map.put("bbb", "bbb");

        String[] strings = new String[]{"b", "c", "d,e,f"};
        String c = post.form(map).execute().body();
        System.out.println(c);
//        post.form("c",new String[]{"b","c","d,e,f"});

    }
}
