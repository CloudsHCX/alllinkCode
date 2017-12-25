package com.alllink.sellerapp.seller.dao;


import com.alllink.sellerapp.seller.entity.SellerActivityEntity;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface SellerActivityDao extends BaseDao<SellerActivityEntity> {

    void updateTotalCost(int sellerId);

    void updateActivityState();
}
