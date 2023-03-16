package com.wa.last;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

/**
 * 基础contorl测试类
 *
 * 
 * @date 2019/9/2 10:01
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BaseControlTest {

    protected MockMvc mockMvc;

    @Resource
    protected WebApplicationContext context;

    /**
     * 初始化,加载容器上下文
     */
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        //shiro失效
//        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.context);
//        this.mockMvc=builder.addFilters((Filter)context.getBean("shiroFilter")).build();
    }
}
