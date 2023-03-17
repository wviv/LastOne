package com.wa.last.mvc.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.wa.last.mvc.model.ResultBean;
import com.wa.last.mvc.pojo.entity.User;
import com.wa.last.mvc.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "用户接口")
@Slf4j
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Resource
    UserServiceImpl userServiceImpl;

    @GetMapping("/info/{id}")
    @ApiOperation("用户信息")
    public ResultBean<User> info(@PathVariable("id") Integer id) {
        return ResultBean.success(userServiceImpl.info(id));
    }

    @GetMapping("/info2222/{id}")
    @ApiOperation("用户信息")
    public ResultBean<String> info(@PathVariable("id") String id, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        List<Cookie> user = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("user")).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(user)) {
            return ResultBean.success("没有获取到用户cookie");
        }
        return ResultBean.success("获取用户消息成功");
    }

    @GetMapping("/login/{id}")
    @ApiOperation("登陆")
    public ResultBean<String> login(@PathVariable("id") String id, HttpServletResponse response) {

        ResponseCookie cookie = ResponseCookie.from("user", "tokentoken")
                .path("/")
                .maxAge(Duration.ofSeconds(604800L))
                .sameSite(SameSiteCookies.STRICT.getValue())
                .build();
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResultBean.success(id);
    }
}
