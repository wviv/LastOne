package com.wa.last.config;

import com.wa.last.mvc.pojo.entity.TestVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
public class TestConfig {

    @Bean
    public TestVO testVO(){
        TestVO testVO = new TestVO();
        return testVO;
    }

    @Bean
    public void testVO2(){
        TestVO testVO = testVO();
        System.out.printf(String.valueOf(testVO));

    }
}
