package com.kunyiduan.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfiguration {

    /**
     * user数据库配置前缀.
     */
    final static String USER_PREFIX = "spring.datasource.druid.user";

    /**
     * points数据库配置前缀.
     */
    final static String POINTS_PREFIX = "spring.datasource.druid.points";


    /**
     * 配置user数据库的数据源
     *
     * @return the data source
     */
    @Bean(name = "UserDataSource")
    @ConfigurationProperties(prefix = USER_PREFIX)  // application.properties中对应属性的前缀
    public DataSource accountDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置points数据库的数据源
     *
     * @return the data source
     */
    @Bean(name = "PointsDataSource")
    @ConfigurationProperties(prefix = POINTS_PREFIX)  // application.properties中对应属性的前缀
    public DataSource redPacketDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
