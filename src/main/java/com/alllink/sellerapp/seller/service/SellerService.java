package com.alllink.sellerapp.seller.service;

import com.alllink.sellerapp.seller.entity.SellerEntity;

import java.sql.Timestamp;
import java.util.HashMap;


public interface SellerService {
    void activeSeller(HashMap<String, Object> map);

    HashMap<String, Object> login(HashMap<String, String> map);

    void update(SellerEntity seller);

    SellerEntity checkPhone(String phoneNumber);

    void register(String phoneNumber, String verificationCode, Timestamp currentTime);

    String getSalt(String phoneNumber);

    SellerEntity findSellerById(int id);

    Double checkBalance(int sellerId);

    void updateByPhoneNumber(HashMap<String, Object> map);
}
