package com.alllink.sellerapp.seller.dao;


import com.alllink.sellerapp.seller.entity.SellerOrderEntity;
import org.springframework.stereotype.Repository;

@Repository("sellerOrderDao")
public interface SellerOrderDao extends BaseDao<SellerOrderEntity> {
}
