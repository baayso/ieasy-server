package com.baayso.springboot.config.mybatis;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * MyBatis基础配置。
 *
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@ConditionalOnBean(DataSource.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Inject
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
            sqlSessionFactory.setDataSource(this.dataSource);
            sqlSessionFactory.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
            sqlSessionFactory.setMapperLocations(resolver.getResources("classpath*:mybatis/**/*.xml"));

            return sqlSessionFactory.getObject();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(this.dataSource);
    }

}
