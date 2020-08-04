package com.aaa.springbootsecuritythymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security配置
 *
 * @author 淮南King
 */
@Configuration @EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter {

    //密码编码器
    @Bean public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //安全拦截机制
    @Override protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            //允许表单登录
            .formLogin()
                //登录页面路径
                .loginPage("/login")
                .loginProcessingUrl("/loginForm")
            //设置登录成功跳转页面，error=true控制页面错误信息的展示
            .successForwardUrl("/index").failureUrl("/login?error=true")
            .permitAll()
            .and()
            //允许不登陆就可以访问的方法，多个用逗号分隔
            .authorizeRequests().antMatchers("/test","/user/**","/register").permitAll()
            //其他的需要授权后访问
            .anyRequest().authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .logout()
            //登录退出
            .logoutUrl("/logout")
            //退出时情况cookies
            .and()
            .logout().deleteCookies("JESSIONID")
            .logoutSuccessUrl("/login?logout");
    }
}
