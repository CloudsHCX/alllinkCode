package com.alllink.userapp.activity.service;

import com.alllink.userapp.activity.entity.ActivityDetailItem;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.entity.ActivityItem;
import com.alllink.userapp.activity.entity.ActivitySearch;

import java.util.List;

public interface ActivityService {
    List<ActivityItem> getDefaultActivity(ActivitySearch activitySearch);
        List<ActivityItem> getActivityByDistance(ActivitySearch activitySearch);
        ActivityDetailItem getActivityInfo(Integer id);

    void updateEnrollNumber(ActivityEntity activityEntity);

}
