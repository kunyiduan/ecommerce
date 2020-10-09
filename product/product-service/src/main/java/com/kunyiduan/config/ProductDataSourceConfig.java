package com.kunyiduan.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.kunyiduan.mapper.product.mapper"}, sqlSessionFactoryRef = "productSqlSessionFactory")
public class ProductDataSourceConfig {

    /**
     * mybatis的xml文件.
     */
    public static final String MAPPER_XML_LOCATION = "classpath*:mapper/product/*.xml";

    @Resource(name = "productDataSource")
    DataSource productDataSource;

    /**
     * 配置Sql Session模板
     */
    @Bean
    public SqlSessionTemplate productSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(productSqlSessionFactory());
    }

    /**
     * 配置SQL Session工厂
     * 使用MybatisSqlSessionFactoryBean替换SqlSessionFactoryBean，解决mybatis-plus的baseMapper多数据源下无法使用的异常
     */
    @Bean
    public SqlSessionFactory productSqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(productDataSource);
        //指定XML文件路径
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
        return factoryBean.getObject();
    }

    /**
     *  配置事务
     */
    @Bean(name="productTransactionManager")
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(productDataSource);
    }

}
