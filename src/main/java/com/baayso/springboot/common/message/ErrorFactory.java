package com.baayso.springboot.common.message;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * 负责构建错误消息
 * @author tanghc (https://gitee.com/durcframework/easyopen)
 *
 */
public class ErrorFactory {

    protected static Logger logger = LoggerFactory.getLogger(ErrorFactory.class);

    private static final String I18N_OPEN_ERROR = "i18n/open/error";

    private static Set<String> noModuleCache = new HashSet<>();

    /** 错误信息的国际化信息 */
    private static MessageSourceAccessor errorMessageSourceAccessor;

    /**
     * 设置国际化资源信息
     */
    public static void initMessageSource(List<String> isvModules) {
        HashSet<String> baseNamesSet = new HashSet<String>();
        baseNamesSet.add(I18N_OPEN_ERROR);
        
        if(!isvModules.isEmpty()) {
            baseNamesSet.addAll(isvModules);
        }
        
        String[] totalBaseNames = baseNamesSet.toArray(new String[0]);

        if (logger.isInfoEnabled()) {
            logger.info("加载错误码国际化资源：{}", StringUtils.arrayToCommaDelimitedString(totalBaseNames));
        }
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setBasenames(totalBaseNames);
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(bundleMessageSource);
        setErrorMessageSourceAccessor(messageSourceAccessor);
    }

    /**
     * 通过ErrorMeta，Locale，params构建国际化错误消息
     * @param errorMeta 错误信息
     * @param locale 本地化
     * @param params 参数
     * @return 如果没有配置国际化消息，则直接返回errorMeta中的信息
     */
    public static Error<String> getError(ErrorMeta errorMeta, Locale locale, Object... params) {
        Assert.notNull(locale, "未设置Locale");
        final String code = errorMeta.getCode();
        String errorMessage = getErrorMessage(errorMeta.getIsvModule() + code, locale, params);
        if(StringUtils.isEmpty(errorMessage)) {
            errorMessage = errorMeta.getMsg();
        }
        final String errorMsg = errorMessage;
        return new Error<String>() {
            @Override
            public String getMsg() {
                return errorMsg;
            }

            @Override
            public String getCode() {
                return code;
            }
        };
    }
    

    public static void setErrorMessageSourceAccessor(MessageSourceAccessor errorMessageSourceAccessor) {
        ErrorFactory.errorMessageSourceAccessor = errorMessageSourceAccessor;
    }

    /**
     * 返回本地化信息
     * @param module 错误模块
     * @param locale 本地化
     * @param params 参数
     * @return 返回信息
     */
    public static String getErrorMessage(String module, Locale locale, Object... params) {
        if (noModuleCache.contains(module)) {
            return null;
        }
        try {
            return errorMessageSourceAccessor.getMessage(module, params, locale);
        } catch (Exception e) {
            noModuleCache.add(module);
            return null;
        }
    }

}
