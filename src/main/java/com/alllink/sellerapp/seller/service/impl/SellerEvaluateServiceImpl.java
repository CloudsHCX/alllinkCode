package com.alllink.sellerapp.seller.service.impl;

import com.alllink.commons.utils.Query;
import com.alllink.sellerapp.seller.dao.SellerEvaluateDao;
import com.alllink.sellerapp.seller.entity.SellerEvaluateEntity;
import com.alllink.sellerapp.seller.service.SellerEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sellerEvaluateService")
public class SellerEvaluateServiceImpl implements SellerEvaluateService {
    @Autowired
    private SellerEvaluateDao sellerEvaluateDao;

    @Override
    public SellerEvaluateEntity findLevelBySellerId(int sellerId) {
        return sellerEvaluateDao.findLevelBySellerId(sellerId);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return sellerEvaluateDao.queryTotal(map);
    }

    @Override
    public List<SellerEvaluateEntity> queryList(Map<String, Object> map) {
        return sellerEvaluateDao.queryList(map);
    }
}
