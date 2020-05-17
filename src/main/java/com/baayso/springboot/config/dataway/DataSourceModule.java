package com.baayso.springboot.config.dataway;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baayso.springboot.common.dataway.interceptor.ApiCacheSpi;
import com.baayso.springboot.common.dataway.interceptor.MyPreExecuteChainSpi;
import com.baayso.springboot.common.dataway.interceptor.MyResultProcessChainSpi;
import com.baayso.springboot.common.dataway.interceptor.QilCacheSpi;

import net.hasor.core.ApiBinder;
import net.hasor.core.DimModule;
import net.hasor.dataway.spi.CompilerSpiListener;
import net.hasor.dataway.spi.PreExecuteChainSpi;
import net.hasor.dataway.spi.ResultProcessChainSpi;
import net.hasor.db.JdbcModule;
import net.hasor.db.Level;
import net.hasor.spring.SpringModule;

/**
 * Hasor 的模块，并且将其交给 Spring 管理。然后把数据源通过 Spring 注入进来。
 *
 * @author ChenFangjie (2020/5/16 20:56)
 * @since 4.0.0
 */
@DimModule
@Component
public class DataSourceModule implements SpringModule {

    @Autowired
    private DataSource dataSource;

    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // DataSource form Spring boot into Hasor
        apiBinder.installModule(new JdbcModule(Level.Full, this.dataSource));

        // 注册接口拦截器
        apiBinder.bindSpiListener(PreExecuteChainSpi.class, new MyPreExecuteChainSpi());
        apiBinder.bindSpiListener(ResultProcessChainSpi.class, new MyResultProcessChainSpi());
        apiBinder.bindSpiListener(PreExecuteChainSpi.class, new ApiCacheSpi());
        apiBinder.bindSpiListener(ResultProcessChainSpi.class, new ApiCacheSpi());
        apiBinder.bindSpiListener(CompilerSpiListener.class, new QilCacheSpi());
    }

}
