package com.alllink.sellerapp.seller.service;

import com.alllink.sellerapp.seller.entity.SellerEntity;

import java.sql.Timestamp;
import java.util.HashMap;


public interface SellerService {
    public void activeSeller(HashMap<String, Object> map);
    public HashMap<String, Object> login(HashMap<String, String> map);
    public void update(SellerEntity seller);
    public SellerEntity checkPhone(String phoneNumber);
    public void register(String phoneNumber, String verificationCode, Timestamp currentTime);
    public void updateCheckcode(String phoneNumber, String verificationCode, Timestamp currentTime);
    public String getSalt(String phoneNumber);
    public SellerEntity findSellerById(int id);
}
