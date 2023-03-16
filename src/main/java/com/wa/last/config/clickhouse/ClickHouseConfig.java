package com.wa.last.config.clickhouse;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import ru.yandex.clickhouse.ClickHouseDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = {"com.wa.last.mvc.dao"}, sqlSessionFactoryRef = "clickHouseSqlSessionFactory")
public class ClickHouseConfig {

    @Bean("clickHouseDataSource")
    public DataSource dataSource() throws IOException {

        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("clickhouse.properties"));
        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(properties.getProperty("url"), properties);
        return clickHouseDataSource;
    }

    @Bean("clickHouseSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
