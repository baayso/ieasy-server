package com.baayso.springboot.config.mybatis;

import java.util.Set;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.lang3.ClassUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.baayso.commons.mybatis.type.EnumValueTypeHandler;
import com.baayso.commons.mybatis.type.ValueEnum;
import com.baayso.commons.spring.ClassScanner;
import com.baayso.springboot.Application;


/**
 * MyBatis基础配置。
 *
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
public class MyBatisConfig implements TransactionManagementConfigurer {

    @Inject
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(this.dataSource);
            sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:config/mybatis-config.xml"));
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mybatis/**/*.xml"));

            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

            TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();

            String[] packages = new String[]{ClassUtils.getPackageName(Application.class)}; // 需要被扫描的包
            Class<?> targetType = ValueEnum.class;
            ClassScanner scanner = new ClassScanner(packages, targetType);
            Set<Class<?>> classes = scanner.getClassSet();

            for (Class<?> clazz : classes) {
                if (clazz.isEnum()) {
                    registry.register(clazz, EnumValueTypeHandler.class);
                }
            }

            return sqlSessionFactory;
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
