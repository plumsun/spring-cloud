package com.itheima.user.mapper;

import com.itheima.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username = #{username}")
    User queryByUsername(@Param("username") String username);
}
