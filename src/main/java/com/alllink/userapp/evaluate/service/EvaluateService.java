package com.alllink.userapp.evaluate.service;

import com.alllink.userapp.evaluate.entity.EvaluateEntity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByActivity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByOne;

import java.util.List;

public interface EvaluateService {
    void add(EvaluateEntity evaluateEntity);

    List<EvaluateEntityByOne> getListByOne(EvaluateEntityByOne evaluateEntityByOne);

    List<EvaluateEntityByActivity> getListByActivity(EvaluateEntityByActivity evaluateEntityByActivity);

}
