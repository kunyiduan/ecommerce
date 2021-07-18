package com.kunyiduan.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.spring.annotation.GlobalTransactionScanner;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan("com.baomidou.springboot.mapper*")
public class SeataAutoConfig {

    @Bean("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        return druidDataSource;
    }

    @Bean
    @Primary
    public DataSourceProxy dataSource(DataSource druidDataSource){
        return new DataSourceProxy(druidDataSource);
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DataSourceProxy dataSourceProxy)throws Exception{
//        //mybatis-plus version
//        MybatisSqlSessionFactoryBean factory = new MybatisSqlSessionFactoryBean();
//        factory.setDataSource(dataSourceProxy);
//        factory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath*:/mapper/*.xml"));
//        return factory.getObject();
//
//        //mybatis version
////       SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
////        sqlSessionFactoryBean.setDataSource(dataSourceProxy);
////        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
////                .getResources("classpath*:/mapper/*.xml"));
////        sqlSessionFactoryBean.setTransactionFactory(new SpringManagedTransactionFactory());
////        return sqlSessionFactoryBean.getObject();
//    }

    /**
     * init global transaction scanner
     *
     * @Return: GlobalTransactionScanner
     */
//    @Bean
//    public GlobalTransactionScanner globalTransactionScanner() {
//        log.info("配置seata........");
//        return new GlobalTransactionScanner("user", "tx_send_point_group");
//    }

}
