package com.baayso.springboot.config.mybatis;

import java.util.Properties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 集成通用Mapper。
 *
 * @since 1.0.0
 */
@Configuration
// 注意：由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.baayso.springboot.common.CommonMapper");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.baayso.springboot.**.dao");
        mapperScannerConfigurer.setMarkerInterface(tk.mybatis.mapper.common.Mapper.class);
        mapperScannerConfigurer.setProperties(properties);

        return mapperScannerConfigurer;
    }

}
