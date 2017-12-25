package com.alllink.userapp.user.service;


import com.alllink.userapp.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void add(User user) throws Exception;

    void update(User user) throws Exception;

    int getListCount(Map<String, Object> map) throws Exception;

    List<User> getList(Map<String, Object> map) throws Exception;

    void updateUserinfo(User user) throws Exception;//插入或者更新用户信息

    User getUserInfo(String phoneNumber);

    User getUserInfoInWeb(Integer userId);

}