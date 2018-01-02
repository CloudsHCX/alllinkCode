package com.alllink.userapp.alipay.service;

import com.alllink.userapp.alipay.entity.AlipayEntity;

import java.util.List;
import java.util.Map;

public interface AlipayService {
    void add(AlipayEntity alipayEntity) throws Exception;
    void update(AlipayEntity alipayEntity) throws Exception;
    int getListCount(Map<String, Object> map) throws Exception;
    List<AlipayEntity> getList(Map<String, Object> map) throws Exception;
}
