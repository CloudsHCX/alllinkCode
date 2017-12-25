package com.alllink.userapp.user.dao;

import com.alllink.userapp.user.entity.User;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


@Repository
public interface UserDao {
    void add(User user);

    void update(User user);

    int getListCount(Map<String, Object> map);

    List<User> getList(Map<String, Object> map);

    User getUserInfo(String phoneNumber);

    void updateUserInfo(User user);//更新用户信息（插入用户信息)

    User getUserInfoInWeb(Integer userId);


}
