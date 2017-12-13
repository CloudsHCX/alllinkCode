package com.alllink.userapp.activity.dao;

import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.entity.ActivitySearch;

import java.util.List;

public interface ActivityDao {
    public List<ActivityEntity> getActivityByDistance(ActivitySearch activitySearch);
    public ActivityEntity getActivityInfo(Integer id);
}
