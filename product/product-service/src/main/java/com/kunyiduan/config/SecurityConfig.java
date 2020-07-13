package com.kunyiduan.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by duankunyi on 2020/7/11.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()
                //普通的接口不需要校验
                .antMatchers("/product/**").permitAll()
                // swagger页面需要添加登录校验
                .antMatchers("/swagger-ui.html").authenticated()
                .and()
                .formLogin();
    }
}
