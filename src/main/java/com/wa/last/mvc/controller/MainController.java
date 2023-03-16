package com.wa.last.mvc.controller;

import com.wa.last.exceptions.AuthenticationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainController {

    public static void main(String[] args) {
//        String regex = "([^a-zA-Z])(^0-9)";
//        String content = "111";
//
//        Pattern p = Pattern.compile(regex);
//        Matcher m = p.matcher(content);
//        System.out.println(m.matches());

        log.error("",new AuthenticationException("打印了个什么玩意"));
    }
}
