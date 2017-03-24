package com.visionet.core.controller;


import com.visionet.core.util.spring.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


/** 
 * @ClassName: BaseController 
 * @Description: TODO: 基本控制器
 * @author liusy@visionet.com.cn * 
 * @param <M>
 */
public abstract class BaseController<M> {
    protected final Class<M> entityClass;

    private String viewPrefix;

    protected BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        setViewPrefix(defaultViewPrefix());
    }
    protected void setCommonData(Model model) {
    }
 
    
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }

    /**
     * 共享的验证规则
     * 验证失败返回true
     *
     * @param m
     * @param result
     * @return
     */
    protected boolean hasError(M m, BindingResult result) {
        Assert.notNull(m);
        return result.hasErrors();
    }

    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }else{
            if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
                backURL = "/"+getViewPrefix()+"/" + backURL;
            }
        }
        return "redirect:"+ backURL;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix) && this.entityClass != null) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }
}
