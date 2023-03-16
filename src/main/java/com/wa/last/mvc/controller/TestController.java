package com.wa.last.mvc.controller;

import com.wa.last.exceptions.AuthenticationException;
import com.wa.last.mvc.pojo.entity.Pd;
import com.wa.last.mvc.model.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(tags = "测试")
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor()
public class TestController {


    @Resource
    RestTemplate restTemplate;

    @GetMapping("/2")
    @ApiOperation("用户信息")
    public void info2() throws IOException {

        List<ResultBean> objects = new ArrayList<>();
        while (true){
            objects.add(ResultBean.success("1"));
        }
    }

    @GetMapping("/4")
    @ApiOperation("测")
    public void restTemplate(HttpServletResponse response)  {
        new Date();
        log.info("gigigigi");
    }

    @GetMapping("/5")
    @ApiOperation("exception")
    public void exception(@RequestParam("giao") String giao,@RequestParam("me") String me, HttpServletResponse response)  {
        throw new AuthenticationException("Giao了");
    }
    @PostMapping("/6")
    @ApiOperation("exception")
    public void dd(@RequestBody Pd pd, HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        throw new AuthenticationException("Giao了");
    }
    @GetMapping("/7/**")
    @ApiOperation("exception")
    public void dd7(HttpServletRequest request) throws IOException {
        throw new AuthenticationException("Giao了");
    }
}
