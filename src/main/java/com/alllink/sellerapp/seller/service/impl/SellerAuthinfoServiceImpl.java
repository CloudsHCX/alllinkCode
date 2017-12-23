package com.alllink.sellerapp.seller.service.impl;

import com.alllink.sellerapp.seller.dao.SellerAuthinfoDao;
import com.alllink.sellerapp.seller.entity.SellerAuthinfoEntity;
import com.alllink.sellerapp.seller.service.SellerAuthinfoService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sellerAuthinfoService")
public class SellerAuthinfoServiceImpl implements SellerAuthinfoService {
    @Autowired
    private SellerAuthinfoDao sellerAuthinfoDao;

    @Override
    public HashMap<String, Object> queryObject(int sellerId){
        return sellerAuthinfoDao.queryObject(sellerId);
    }

    @Override
    public List<SellerAuthinfoEntity> queryList(Map<String, Object> map){
        return sellerAuthinfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map){
        return sellerAuthinfoDao.queryTotal(map);
    }

    @Override
    public void save(SellerAuthinfoEntity sellerAuthinfo){
        sellerAuthinfoDao.save(sellerAuthinfo);
    }

    @Override
    public void update(SellerAuthinfoEntity sellerAuthinfo){
        sellerAuthinfoDao.update(sellerAuthinfo);
    }

    @Override
    public void delete(int sauthinfoId){
        sellerAuthinfoDao.delete(sauthinfoId);
    }


}
