package com.alllink.sellerapp.seller.service.impl;

import com.alllink.commons.utils.RandomNumberUtil;
import com.alllink.sellerapp.seller.dao.SellerActivityDao;
import com.alllink.sellerapp.seller.dao.SellerDao;
import com.alllink.sellerapp.seller.entity.SellerEntity;
import com.alllink.sellerapp.seller.service.SellerActivityService;
import com.alllink.sellerapp.seller.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;

@Service("sellerService")
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerDao sellerDao;
    @Autowired
    private SellerActivityDao sellerActivityDao;
    @Override
    public void activeSeller(HashMap<String, Object> map) {

        sellerDao.activeSeller(map);
    }



    @Override
    public HashMap<String, Object> login(HashMap<String, String> map) {
         return sellerDao.login(map);
    }

    @Override
    public void update(SellerEntity seller) {
        sellerDao.update(seller);
    }

    @Override
    public SellerEntity checkPhone(String phoneNumber) {
        return sellerDao.checkPhone(phoneNumber);
    }

    @Override
    public void register(String phoneNumber, String verificationCode, Timestamp currentTime) {
        String salt = RandomNumberUtil.CreateSalt();
        sellerDao.register(phoneNumber, verificationCode, currentTime, salt);
    }


    @Override
    public String getSalt(String phoneNumber) {
        return sellerDao.getSalt(phoneNumber);

    }

    @Override
    public SellerEntity findSellerById(int id) {
        return sellerDao.findSellerById(id);
    }


    /*
    * 查询余额
    * 1.首先查询结束的活动，计算orderu表中结束活动的所有用户支付总金额，赋值给活动总收益（total_cost）；
    * 2.更新结束活动的总金额到商家余额中（即活动状态为3的活动）；
    * 3.更新计算过活动总金额到商家余额中的活动状态（从状态3更新为状态4）；
    * 4.查询商家取款表（seller_order），用户余额中减去取款金额（即状态为0的取款信息）；
    * */
    @Transactional
    @Override
    public Double checkBalance(int sellerId) {
        sellerActivityDao.updateTotalCost(sellerId);
        sellerDao.updateBalance(sellerId);
        sellerActivityDao.updateActivityState();
        return sellerDao.checkBalance(sellerId);
    }

    @Override
    public void updateByPhoneNumber(HashMap<String, Object> map) {
        sellerDao.updateByPhoneNumber(map);
    }


}
