package com.kunyiduan.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisConfig {

    /**
     * brand数据库配置前缀.
     */
    final static String BRAND_PREFIX = "spring.datasource.druid.brand";

    /**
     * product数据库配置前缀.
     */
    final static String PRODUCT_PREFIX = "spring.datasource.druid.product";


    /**
     * 配置brand数据库的数据源
     *
     * @return the data source
     */
    @Bean(name = "BrandDataSource")
    @ConfigurationProperties(prefix = BRAND_PREFIX)  // application.properties中对应属性的前缀
    public DataSource brandDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置product数据库的数据源
     *
     * @return the data source
     */
    @Bean(name = "ProductDataSource")
    @ConfigurationProperties(prefix = PRODUCT_PREFIX)  // application.properties中对应属性的前缀
    public DataSource productDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
