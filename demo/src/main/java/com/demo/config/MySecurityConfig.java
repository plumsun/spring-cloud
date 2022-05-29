package com.demo.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description: Security简单配置
 * @date: 2022/5/29 22:05
 * @author: LiHaoHan
 * @program: com.demo.config
 */
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    //认证
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //从内存中获取用户数据
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                //指定用户名
                .withUser("")
                //指定密码（新版密码需要进行加密操作）
                .password(new BCryptPasswordEncoder().encode(""))
                //指定当前用户的权限
                .roles();

        //从数据库中获取用户数据
        auth.jdbcAuthentication().dataSource(new MysqlDataSource());
    }

    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //对请求进行授权
        http.authorizeRequests()
                //指定授权页面
                .antMatchers()
                //所有用户都可以访问
                .permitAll()
                .antMatchers()
                //授权指定用户
                .hasRole("");

        //无权限重定向到登录页面
        http.formLogin()
                //指定登录页面
                .loginPage("");
    }

}
