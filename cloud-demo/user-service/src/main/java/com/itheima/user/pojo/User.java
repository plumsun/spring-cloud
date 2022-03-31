package com.itheima.user.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @作者 itcast
 * @创建日期 2021/3/21 10:36
 **/
@Data
public class User {
    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sourceType;

    private String nickName;

    private String name;

    private String status;

    private String headPic;

    private String qq;

    private String isMobileCheck;

    private String isEmailCheck;

    private String sex;

    private Integer userLevel;

    private Integer points;

    private Integer experienceValue;

    private Date birthday;

    private Date lastLoginTime;

    private static final long serialVersionUID = 1L;
}