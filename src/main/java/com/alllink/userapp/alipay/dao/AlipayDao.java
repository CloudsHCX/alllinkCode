package com.alllink.userapp.alipay.dao;

import com.alllink.userapp.alipay.entity.AlipayEntity;


import java.util.List;
import java.util.Map;

public interface AlipayDao {
    void add(AlipayEntity alipayEntity);
    void update(AlipayEntity alipayEntity);
    int getListCount(Map<String, Object> map);
    List<AlipayEntity> getList(Map<String, Object> map);
}
