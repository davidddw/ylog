package com.yunshan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yunshan.bean.User;

public interface UserMapper {
    String MQL_GET_ALL_USERS  = "select * from user_v2_2";
    String MQL_GET_USER_BY_NAME = "select id, username, useruuid from user_v2_2 where username=#{username}";
    String MQL_GET_USER_BY_ID = "select id, username, useruuid from user_v2_2 where id=#{id}";

    @Select(MQL_GET_USER_BY_NAME)
    public User getUserByName(String username) throws Exception;
    
    @Select(MQL_GET_USER_BY_ID)
    public User getUserByID(int id) throws Exception;
    
    @Select(MQL_GET_ALL_USERS)
    public List<User> getUsers() throws Exception;
    
}
