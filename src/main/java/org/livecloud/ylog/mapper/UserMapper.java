package org.livecloud.ylog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.User;

public interface UserMapper {
	
	User getUserByUsername(@Param("name") String username);

    User getUser(@Param("id") long id);

    long deleteUser(@Param("id") long id);

    long updateUser(User user);
    
    long addUser(User user);

    List<User> getAllUsers(RowBounds rowBounds);

    List<User> getAllUsers();

    long getCount();
}
