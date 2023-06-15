package com.vn.assignmentjava5.config;

import com.vn.assignmentjava5.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

public class WebMVCconfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/login", "/register");

        LocaleChangeInterceptor changeInterceptor  = new LocaleChangeInterceptor();
        changeInterceptor.setParamName("language");
        registry.addInterceptor(changeInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/**");
    }
}
