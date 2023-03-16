package com.wa.last.ws.amqp;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 虚拟主机自动配置
 */
@Slf4j
@Configuration
public class VirtualHostConfiguration implements CommandLineRunner {

    private String username;

    private String password;

    private String url;

    private String virtualHost;

    @Value("${rabbitmq.apiPort:15672}")
    private String apiPort;

    @Resource
    RabbitProperties rabbitProperties;

    public static final String baseVirtualHostUrl = "/api/vhosts";

    /**
     * 默认虚拟主机
     */
    public static final String defaultVirtualHost = "/";

    public static final String errorKeyWord = "Error request";


    //虚拟主机相关

    /**
     * 虚拟主机相关
     * 如果设置了虚拟主机 且  服务器没有相应主机 则会创建相应主机
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        if (Objects.isNull(rabbitProperties)){
            return;
        }

        virtualHost = rabbitProperties.getVirtualHost();

        if (StringUtils.isBlank(virtualHost) || StringUtils.equalsIgnoreCase(defaultVirtualHost,virtualHost)){
            return;
        }

        username = rabbitProperties.getUsername();
        password = rabbitProperties.getPassword();

        String host = rabbitProperties.getHost();
        if (StringUtils.isBlank(host)){
            log.error("rabbitmq host is null");
            return;
        }

        url = host + ":" + apiPort + baseVirtualHostUrl;

        //api/vhosts 读取所有虚拟主机

        List<VirtualHostInfo> virtualHostInfos = allVirtualHosts();

        //没查到数据，或者已存在相应虚拟主机直接 返回
        if (CollectionUtils.isEmpty(virtualHostInfos) || virtualHostInfos.stream().anyMatch(virtualHostInfo -> StringUtils.equalsIgnoreCase(virtualHostInfo.getName(),virtualHost))){
            return;
        }

        //PUT /api/vhosts/vh1 创建vh1 虚拟主机

        createVirtualHosts(virtualHost);

    }


    //	查询服务器所有虚拟主机
    private List<VirtualHostInfo>  allVirtualHosts() {

        String body = HttpRequest.get(url).basicAuth(username, password).execute().body();
        if (StringUtils.isNotBlank(body) && body.contains(errorKeyWord)){
            throw new RuntimeException(body);
        }
        return JSON.parseArray(body, VirtualHostInfo.class);
    }

    //	创建虚拟主机
    public String  createVirtualHosts(String hostname) {

        String body = HttpRequest.put(url + "/" + hostname).basicAuth(username, password).execute().body();

        if (StringUtils.isNotBlank(body) && body.contains(errorKeyWord)){
            throw new RuntimeException(body);
        }
        log.info("创建虚拟主机{}成功",virtualHost);

        return "success";
    }

    //DELETE /api/vhosts/vh1 删除vh1 虚拟主机
    public String  deleteVirtualHosts(String hostName) {

        String body = HttpRequest.delete(url + "/" + hostName).basicAuth(username, password).execute().body();
        if (StringUtils.isNotBlank(body) && body.contains(errorKeyWord)){
            throw new RuntimeException(body);
        }
        log.info("删除虚拟主机{}成功",virtualHost);

        return "success";
    }
}
