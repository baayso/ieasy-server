package com.baayso.springboot.config.dataway;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ConfigurationProperties(prefix = I18nProperties.I18N_PREFIX)
public class I18nProperties {

    public static final String I18N_PREFIX = "i18n";

    /** 消息模块 */
    private List<String> isvModules = Collections.emptyList();

    public List<String> getIsvModules() {
        return isvModules;
    }

    public void setIsvModules(List<String> isvModules) {
        this.isvModules = isvModules;
    }

}
