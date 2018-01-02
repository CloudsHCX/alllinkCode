package com.alllink.userapp.activity.dao;

import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.entity.ActivitySearch;

import java.util.List;

public interface ActivityDao {
    List<ActivityEntity> getActivityByDistance(ActivitySearch activitySearch);
    ActivityEntity getActivityInfo(Integer id);

    void updateEnrollNumber(ActivityEntity activityEntity);

}
