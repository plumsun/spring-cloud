package com.demo.user.mapper;

import com.demo.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from tb_user where username = #{username}")
    User queryByUsername(@Param("username") String username);
}
