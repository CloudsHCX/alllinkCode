package com.alllink.sellerapp.seller.service.impl;

import com.alllink.commons.utils.Query;
import com.alllink.sellerapp.seller.dao.ActivityOrderDao;
import com.alllink.sellerapp.seller.entity.ActivityOrderEntity;
import com.alllink.sellerapp.seller.service.ActivityOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("activityOrderService")
public class ActivityOrderServiceImpl implements ActivityOrderService {
    @Autowired
    private ActivityOrderDao activityOrderDao;

    @Transactional
    @Override
    public List<ActivityOrderEntity> queryList(HashMap<String, Object> map) {
        return activityOrderDao.queryList(map);
    }

    @Override
    public int queryTotal(Query query) {
        return activityOrderDao.queryTotal(query);
    }
}
