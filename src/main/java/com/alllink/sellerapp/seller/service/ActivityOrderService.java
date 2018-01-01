package com.alllink.sellerapp.seller.service;

import com.alllink.commons.utils.Query;
import com.alllink.sellerapp.seller.entity.ActivityOrderEntity;

import java.util.HashMap;
import java.util.List;

public interface ActivityOrderService {
    List<ActivityOrderEntity> queryList(HashMap<String, Object> map);

    int queryTotal(Query query);
}
