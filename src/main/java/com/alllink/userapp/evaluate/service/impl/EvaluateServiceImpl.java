package com.alllink.userapp.evaluate.service.impl;

import com.alllink.userapp.evaluate.dao.EvaluateDao;
import com.alllink.userapp.evaluate.entity.EvaluateEntity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByActivity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByOne;
import com.alllink.userapp.evaluate.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateDao evaluateDao;

    @Override
    public void add(EvaluateEntity evaluateEntity) {

        evaluateDao.add(evaluateEntity);
    }

    @Override
    public List<EvaluateEntityByOne> getListByOne(EvaluateEntityByOne evaluateEntityByOne) {
        return evaluateDao.getListByOne(evaluateEntityByOne);
    }

    @Override
    public List<EvaluateEntityByActivity> getListByActivity(EvaluateEntityByActivity evaluateEntityByActivity) {
        return evaluateDao.getListByActivity(evaluateEntityByActivity);
    }
}
