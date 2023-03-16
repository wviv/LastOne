package com.wa.last;

import com.wa.last.filter.AuthenticationFilter;
import com.wa.last.log.ControlLog;
import com.wa.last.mvc.pojo.entity.HocRequestLoggingFilter;
import com.wa.last.utils.TimeDateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sun.org.apache.xerces.internal.impl.xs.util.StringListImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author wangwenchang
 * @date 2018/4/25 11:07
 */
@SpringBootApplication
@EnableEurekaClient
@ControlLog
@MapperScan("com.wa.last.mvc.mapper")
@EnableFeignClients
public class ClientApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8:00"));

        SpringApplication.run(ClientApplication.class, args);
    }


    @Bean
    public RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        // key 序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // value 序列化
        redisTemplate.setValueSerializer(RedisSerializer.string());
        // hash 类型 key序列化
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        // hash 类型 value序列化方式
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
//        // 注入连接工厂
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        // 让设置生效
//        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public AuthenticationFilter authenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean(authenticationFilter());
        registrationBean.setOrder(19);
        registrationBean.setUrlPatterns(new StringListImpl(new String[]{"/**"},1));
        return registrationBean;
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }


    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);/** null值也返回 */
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); /** 未知入参不报错 */
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        mapper.registerModule(timeModule);
        mapper.setDateFormat(new SimpleDateFormat("") {
            @Override
            public Date parse(String source) throws ParseException {
                return TimeDateUtils.strToDate(source);
            }

            @Override
            public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
                return new StringBuffer(TimeDateUtils.dateToStr2(date));
            }
        });

        converter.setObjectMapper(mapper);
        List<MediaType> list = new ArrayList<>();
        list.addAll(converter.getSupportedMediaTypes());
        list.add(MediaType.TEXT_PLAIN);
        list.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(list);
        return converter;
    }

//    @Bean
    public HocRequestLoggingFilter commonsRequestLoggingFilter(){
        return new HocRequestLoggingFilter();
    }
}
