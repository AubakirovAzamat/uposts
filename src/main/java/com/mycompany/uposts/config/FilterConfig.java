package com.mycompany.uposts.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mycompany.uposts.filter.AuthorizationFilter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        final var filterBean = new FilterRegistrationBean<>(new AuthorizationFilter());
        filterBean.addUrlPatterns(
                "/post-service-public/search/*",
                "/post-service-public/communication/*",
                "/post-service-public/user/getMyPosts",
                "/post-service-public/user/publicPost");
        return filterBean;
    }
}