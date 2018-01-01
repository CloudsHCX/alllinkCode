package com.alllink.sellerapp.seller.service.impl;


import com.alllink.sellerapp.seller.dao.SellerOrderDao;
import com.alllink.sellerapp.seller.service.SellerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("sellerOrderService")
public class SellerOrderServiceImpl implements SellerOrderService {
    @Autowired
    private SellerOrderDao sellerOrderDao;

    @Override
    public void save(HashMap<String, Object> map) {
        sellerOrderDao.save(map);
    }
}
