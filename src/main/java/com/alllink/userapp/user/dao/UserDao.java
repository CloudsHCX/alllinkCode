package com.alllink.userapp.user.dao;

import com.alllink.userapp.user.entity.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Repository
public interface UserDao {
   public void add(User user);
   public void update(User user);
   public int getListCount(Map<String, Object> map);
   public List<User> getList(Map<String, Object> map);
   public User getUserInfo(String phoneNumber);
   public void updateUserInfo(User user);//更新用户信息（插入用户信息)

}
