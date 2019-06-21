package com.baayso.springboot.config.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

/**
 * Mybatis-Plus 配置。
 *
 * @author ChenFangjie (2017/12/4 21:14)
 * @since 2.0.0
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.baayso.**.dao*")
public class MybatisPlusConfig {

    /** SQL执行效率插件 */
    @Bean
    @Profile({"dev", "test"}) // 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /** 乐观锁插件 */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * mybatis-plus 分页插件
     * <p>
     * 文档：http://mp.baomidou.com/guide/tenant.html
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {

        /*
         * 【测试多租户】 SQL 解析处理拦截器
         * 这里固定写成租户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）
         */
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "tenant_id";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                // 这里可以判断是否过滤表
                if ("user".equals(tableName)) {
                    return true;
                }

                return false;
            }
        });

        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(tenantSqlParser);

        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setSqlParserList(sqlParserList);
        //paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
        //    @Override
        //    public boolean doFilter(MetaObject metaObject) {
        //        MappedStatement ms = PluginUtils.getMappedStatement(metaObject);
        //        // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
        //        if ("com.baomidou.springboot.mapper.UserMapper.selectListBySQL".equals(ms.getId())) {
        //            return true;
        //        }
        //
        //        return false;
        //    }
        //});

        return paginationInterceptor;
    }

}
