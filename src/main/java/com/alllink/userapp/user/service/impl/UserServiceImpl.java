package com.alllink.userapp.user.service.impl;

import com.alllink.userapp.user.entity.User;
import com.alllink.userapp.user.dao.UserDao;
import com.alllink.userapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {
        userDao.add(user);
    }


    @Override
    public void update(User user) throws Exception { userDao.update(user);}

    @Override
    public int getListCount(Map<String, Object> map) {
        return userDao.getListCount(map);
    }

    @Override
    public List<User> getList(Map<String, Object> map) throws Exception {
        return userDao.getList( map );
    }

    @Override
    public void updateUserinfo(User user) throws Exception {
        userDao.updateUserInfo(user);
    }

    @Override
    public User getUserInfo(String phoneNumber) {
        return userDao.getUserInfo(phoneNumber);
    }

    @Override
    public User getUserInfoInWeb(Integer userId) {
        return userDao.getUserInfoInWeb(userId);
    }

    @Override
    public void updatetotalPoints(User user) {
        userDao.updatetotalPoints(user);
    }



}
