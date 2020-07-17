//package com.kunyiduan.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = {"com.kunyiduan.mapper.points.mapper"}, sqlSessionFactoryRef = "pointsSqlSessionFactory")
//public class PointsDataSourceConfiguration {
//
//    /**
//     * mybatis的xml文件.
//     */
//    public static final String MAPPER_XML_LOCATION = "classpath*:com/kunyiduan/mapper/points/mapper/*.xml";
//
//    @Autowired
//    @Qualifier("PointsDataSource")
//    DataSource pointsDataSource;
//
//    /**
//     * 配置Sql Session模板
//     */
//    @Bean
//    public SqlSessionTemplate springSqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(accountSqlSessionFactory());
//    }
//
//    /**
//     * 配置SQL Session工厂
//     */
//    @Bean
//    public SqlSessionFactory accountSqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(pointsDataSource);
//        //指定XML文件路径
//        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
//        return factoryBean.getObject();
//    }
//
//    /**
//     *  配置事务
//     */
//    @Bean(name="transactionManager")
//    public DataSourceTransactionManager transactionManager(){
//        return new DataSourceTransactionManager(pointsDataSource);
//    }
//}
