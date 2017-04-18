package com.baayso.springboot.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

import lombok.Getter;
import lombok.Setter;

/**
 * Druid数据源配置。
 *
 * @author ChenFangjie (2017/4/18 14:26)
 * @since 1.0.0
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "datasource")
public class DataSourceConfig {

    private String url;
    private String username;
    private String password;

    private int initialSize;
    private int minIdle;
    private int maxActive;
    private int maxWait;

    private long timeBetweenEvictionRunsMillis;
    private long minEvictableIdleTimeMillis;

    private String  validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;

    private boolean poolPreparedStatements;
    private int     maxOpenPreparedStatements;

    private boolean removeAbandoned;
    private long    removeAbandonedTimeoutMillis;
    private boolean logAbandoned;

    private String filters;

    @Bean
    public DataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();

        ds.setUrl(this.url);
        ds.setUsername(this.username);
        ds.setPassword(this.password);

        ds.setInitialSize(this.initialSize);
        ds.setMinIdle(this.minIdle);
        ds.setMaxActive(this.maxActive);
        ds.setMaxWait(this.maxWait);

        ds.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        ds.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);

        ds.setValidationQuery(this.validationQuery);
        ds.setTestWhileIdle(this.testWhileIdle);
        ds.setTestOnBorrow(this.testOnBorrow);
        ds.setTestOnReturn(this.testOnReturn);

        ds.setPoolPreparedStatements(this.poolPreparedStatements);
        ds.setMaxOpenPreparedStatements(this.maxOpenPreparedStatements);

        ds.setRemoveAbandoned(this.removeAbandoned);
        ds.setRemoveAbandonedTimeoutMillis(this.removeAbandonedTimeoutMillis);
        ds.setLogAbandoned(this.logAbandoned);

        try {
            ds.setFilters(this.filters);

            ds.init();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ds;
    }

    /** 配置Druid和Spring关联监控配置 */
    @Bean
    public RegexpMethodPointcutAdvisor regexpMethodPointcutAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(new DruidStatInterceptor());
        advisor.setPatterns("com.baayso..service.*", "com.baayso..dao.*");

        return advisor;
    }

}
