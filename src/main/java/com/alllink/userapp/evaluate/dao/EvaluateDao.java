package com.alllink.userapp.evaluate.dao;

import com.alllink.userapp.evaluate.entity.EvaluateEntity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByActivity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByOne;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluateDao {
    void add(EvaluateEntity evaluateEntity);

    List<EvaluateEntityByOne> getListByOne(EvaluateEntityByOne evaluateEntityByOne);

    List<EvaluateEntityByActivity> getListByActivity(EvaluateEntityByActivity evaluateEntityByActivity);

}
