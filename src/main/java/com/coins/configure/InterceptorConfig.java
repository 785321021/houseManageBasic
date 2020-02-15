package com.coins.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coins.configure.jwt.AuthenticationInterceptor;
import com.coins.configure.jwt.CorsInterceptor;

@Component
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//pc端拦截器
    	registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
        InterceptorRegistration ad = registry.addInterceptor(authenticationInterceptor());
        ad.excludePathPatterns("/c-api/errlog");
        ad.excludePathPatterns("/c-api/login");
        ad.excludePathPatterns("/c-api/logout");
        ad.excludePathPatterns("/c-api/jqdcCommon/*");
        ad.addPathPatterns("/c-api/**");
        
//        //pid 端
//        InterceptorRegistration ri = registry.addInterceptor(requestInterceptor());
//        ri.excludePathPatterns("/error");
//        ri.addPathPatterns("/api/**");
//        
    }
    /**
     * pc权限
     * @return
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
//    /**
//     * pid 权限
//     * @return
//     */
//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor();
//    }
    /**
     * pc端跨域访问
     * @return
     */
    @Bean
    public CorsInterceptor corsInterceptor() {
        return new CorsInterceptor();
    }
}