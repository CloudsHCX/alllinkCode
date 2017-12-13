package com.alllink.userapp.user.service;


import com.alllink.userapp.user.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    public void add(User user) throws  Exception;
    public void update(User user) throws  Exception;
    public int getListCount(Map<String, Object> map) throws Exception;
    public List<User> getList(Map<String, Object> map) throws Exception;
    public void updateUserinfo(User user) throws Exception;//插入或者更新用户信息
    public User getUserInfo(String phoneNumber);

}