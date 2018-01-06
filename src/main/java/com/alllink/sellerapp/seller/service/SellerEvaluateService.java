package com.alllink.sellerapp.seller.service;

import com.alllink.commons.utils.Query;
import com.alllink.sellerapp.seller.entity.SellerEvaluateEntity;

import java.util.List;
import java.util.Map;

public interface SellerEvaluateService {
    SellerEvaluateEntity findLevelBySellerId(int sellerId);

    int queryTotal(Map<String, Object> map);

    List<SellerEvaluateEntity> queryList(Map<String, Object> map);
}
