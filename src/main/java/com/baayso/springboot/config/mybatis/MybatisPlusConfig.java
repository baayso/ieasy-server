package com.baayso.springboot.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baayso.springboot.common.mybatis.InsertAndUpdateMetaObjectHandler;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;

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

    /** 插入与更新时自动填充字段处理器 */
    @Bean
    public InsertAndUpdateMetaObjectHandler insertAndUpdateMetaObjectHandler() {
        return new InsertAndUpdateMetaObjectHandler();
    }

    /**
     * mybatis-plus 插件
     * <p>
     * 文档：https://baomidou.com/pages/2976a3/
     * <p>
     * 使用多个功能需要注意顺序关系,建议使用如下顺序<br>
     * 1) 多租户,动态表名<br>
     * 2) 分页,乐观锁<br>
     * 3) sql 性能规范,防止全表更新与删除<br>
     * 总结: 对 sql 进行单次改造的优先放入,不对 sql 进行改造的最后放入
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 防止全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());

        // 多租户插件
        TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
        // 行级租户SQL解析器
        tenantLineInnerInterceptor.setTenantLineHandler(new BasicTenantLineHandler());
        interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

        // 分页插件
        // 如果使用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return interceptor;
    }

}
