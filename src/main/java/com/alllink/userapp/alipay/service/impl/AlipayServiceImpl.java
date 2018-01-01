package com.alllink.userapp.alipay.service.impl;

import com.alllink.userapp.alipay.dao.AlipayDao;
import com.alllink.userapp.alipay.entity.AlipayEntity;
import com.alllink.userapp.alipay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    private AlipayDao alipayDao;

    @Override
    public void add(AlipayEntity alipayEntity) {
        alipayDao.add(alipayEntity);
    }

    @Override
    public void update(AlipayEntity alipayEntity) throws Exception {
        alipayDao.update(alipayEntity);
    }

    @Override
    public int getListCount(Map<String, Object> map) throws Exception {
        return alipayDao.getListCount(map);
    }

    @Override
    public List<AlipayEntity> getList(Map<String, Object> map) throws Exception {
        return alipayDao.getList(map);
    }


}
