package com.wa.last.mvc.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Api(tags = "valid")
@Slf4j
@RestController
@RequestMapping("/security")
public class SecurityController {


    @PostMapping("/1")
    @ApiOperation("用户信息")
    public void v1() throws IOException, MethodArgumentNotValidException {
//        throw new MethodArgumentNotValidException(null,bindingResult);
//        log.info("滴答滴答就消逝了");
//        jwt.get
    }
}
