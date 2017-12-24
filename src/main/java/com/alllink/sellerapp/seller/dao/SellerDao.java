package com.alllink.sellerapp.seller.dao;

import com.alllink.sellerapp.seller.entity.SellerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.HashMap;

@Repository
public interface SellerDao {
    void activeSeller(HashMap<String, Object> map);

    HashMap<String, Object> login(HashMap<String, String> map);

    void update(SellerEntity seller);

    SellerEntity checkPhone(String phoneNumber);

    void register(@Param("phoneNumber") String phoneNumber, @Param("verificationCode") String verificationCode, @Param("currentTime") Timestamp currentTime, @Param("salt") String salt);

    void updateCheckcode(@Param("phoneNumber") String phoneNumber, @Param("verificationCode") String verificationCode, @Param("currentTime") Timestamp currentTime);

    String getSalt(String phoneNumber);

    SellerEntity findSellerById(int id);

    Double checkBalance(int sellerId);
}
