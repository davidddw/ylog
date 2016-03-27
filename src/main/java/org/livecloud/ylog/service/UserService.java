package org.livecloud.ylog.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.livecloud.ylog.entity.User;
import org.livecloud.ylog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserService {
    
    @Autowired
	private UserMapper userMapper;
	
	public User findUserByName(String name) {
		return userMapper.getUserByUsername(name);
	}
	
	public long getUserCount() {
		return userMapper.getCount();
	}
	
	public User findById(long userId) {
		return userMapper.getUser(userId);
	}
	
	public List<User> findAllUsers() {
		return userMapper.getAllUsers();
	}
	
	public PageInfo<User> findAllUsers(int pageIndex, int pageSize) {
	    List<User> list = userMapper.getAllUsers(new RowBounds(pageIndex, pageSize));
	    return new PageInfo<User>(list);
	}
	
	public long addNewUser(User user) {
		if(userMapper.getUserByUsername(user.getName())!=null){
			return 0; 
		} else {
			return userMapper.addUser(user);
		}
	}
	
	public long updateUser(User user){
		return userMapper.updateUser(user);
	}
	
	public boolean removeUserById(long userId) {
		User u = userMapper.getUser(userId);
		if(u == null){
			return false;
		}else {
		    userMapper.deleteUser(userId);
			return true;
		}	
	}
}