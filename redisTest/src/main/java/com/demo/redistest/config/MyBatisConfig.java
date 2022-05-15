package com.demo.redistest.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @date: 2022/5/15 15:22
 * @author: LiHaoHan
 * @program: com.demo.redistest.config
 */
@Configuration
public class MyBatisConfig {

    @Autowired
    private MyBatisIntercept myBatisIntercept;


    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        //将自定义拦截器装入mybatis
        bean.setPlugins(myBatisIntercept);
        return bean;
    }
}
