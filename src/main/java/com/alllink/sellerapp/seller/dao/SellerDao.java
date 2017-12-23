package com.alllink.sellerapp.seller.dao;

import com.alllink.sellerapp.seller.entity.SellerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;

@Repository
public interface SellerDao {
    public void activeSeller(HashMap<String, Object> map);
    //public SellerEntity login(@Param("sellerName")String sellerName,@Param("password")String password);
    public HashMap<String, Object> login(HashMap<String, String> map);
    public void update(SellerEntity seller);
    public SellerEntity checkPhone(String phoneNumber);

    public void register(@Param("phoneNumber")String phoneNumber, @Param("verificationCode")String verificationCode, @Param("currentTime")Timestamp currentTime, @Param("salt")String salt);
    public void updateCheckcode(@Param("phoneNumber")String phoneNumber, @Param("verificationCode")String verificationCode, @Param("currentTime")Timestamp currentTime);
    public String getSalt(String phoneNumber);

    public SellerEntity findSellerById(int id);
}
