package com.alllink.sellerapp.seller.dao;

import com.alllink.sellerapp.seller.entity.SellerEvaluateEntity;
import org.springframework.stereotype.Repository;

@Repository("sellerEvaluateDao")
public interface SellerEvaluateDao extends BaseDao<SellerEvaluateEntity> {
    SellerEvaluateEntity findLevelBySellerId(int sellerId);
}
