package com.alllink.userapp.activity.service.impl;

import com.alllink.commons.enums.ActivityType;
import com.alllink.commons.utils.ConvertDisToLonAndLat;
import com.alllink.userapp.activity.dao.ActivityDao;
import com.alllink.userapp.activity.entity.*;
import com.alllink.userapp.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityDao activityDao;
    @Override
    public List<ActivityItem> getDefaultActivity(ActivitySearch activitySearch){
        List<ActivityEntity> activityList = activityDao.getActivityByDistance(activitySearch);
        if (activityList==null||activityList.size()==0){
            return new ArrayList<ActivityItem>();
        }
        //设置每个活动到用户的距离
        for (ActivityEntity ae :activityList){
            double distance = ConvertDisToLonAndLat.getDistance(activitySearch.getLng(),activitySearch.getLat(),
                    ae.getLongitude(),ae.getLatitude());
            ae.setDistance(distance);
        }
        // 根据用户的需要进行排序
        Collections.sort(activityList,new ActivityComparator(activitySearch.getSortParams()));
        List<ActivityItem> itemList = new ArrayList<>();
        for (ActivityEntity activity:activityList){
            ActivityItem item = new ActivityItem();
            item.setActivityId(activity.getActivityId().toString());
            item.setActivityName(activity.getActivityName());
            item.setActivityType(activity.getActivityType().toString());
            item.setCost(activity.getCost().toString());
            item.setEnrollNumber(activity.getEnrollNumber().toString());
            item.setTotalNumber(activity.getTotalNumber().toString());
            if (activity.getActivityPhoto() != null) {
                item.setImageUrl(activity.getActivityPhoto().split(",")[0]);
            }
            item.setDistance(activity.getDistance().toString());
            item.setActivityTypenName(ActivityType.getNameByValue(activity.getActivityType()));
            itemList.add(item);
        }
        return itemList;
    }


    @Override
    public List<ActivityItem> getActivityByDistance(ActivitySearch activitySearch) {
        //获取在此范围内的最小和最大经纬度
        Map<String,Double> map = ConvertDisToLonAndLat.getLonAndLat(activitySearch.getLng(),activitySearch.getLat(),activitySearch.getDistance());
        Double minLon = map.get("minLon");
        Double maxLon = map.get("maxLon");
        Double minLat = map.get("minLat");
        Double maxLat = map.get("maxLat");
        activitySearch.setMinLon(minLon);
        activitySearch.setMaxLon(maxLon);
        activitySearch.setMinLat(minLat);
        activitySearch.setMaxLat(maxLat);
        //查询数据
        List<ActivityEntity> activityList = activityDao.getActivityByDistance(activitySearch);
        if (activityList==null||activityList.size()==0){
            return new ArrayList<ActivityItem>();
        }
        //设置每个活动到用户的距离
        for (ActivityEntity ae :activityList){
            double distance = ConvertDisToLonAndLat.getDistance(activitySearch.getLng(),activitySearch.getLat(),
                    ae.getLongitude(),ae.getLatitude());
            ae.setDistance(distance);
        }
        // 根据用户的需要进行排序
        Collections.sort(activityList,new ActivityComparator(activitySearch.getSortParams()));
        List<ActivityItem> itemList = new ArrayList<>();
        for (ActivityEntity activity:activityList){
            ActivityItem item = new ActivityItem();
            item.setActivityId(activity.getActivityId().toString());
            item.setActivityName(activity.getActivityName());
            item.setActivityType(activity.getActivityType().toString());
            item.setCost(activity.getCost().toString());
            item.setEnrollNumber(activity.getEnrollNumber().toString());
            item.setTotalNumber(activity.getTotalNumber().toString());
            if (activity.getActivityPhoto() != null) {
                item.setImageUrl(activity.getActivityPhoto().split(",")[0]);
            }
            item.setDistance(activity.getDistance().toString());
            item.setActivityTypenName(ActivityType.getNameByValue(activity.getActivityType()));
            itemList.add(item);
        }
        return itemList;
    }

    @Override
    public ActivityDetailItem getActivityInfo(Integer id) {
        ActivityDetailItem activityItem = new ActivityDetailItem();
        ActivityEntity activity = activityDao.getActivityInfo(id);
        if(activity==null){
            return null;
        }
        activityItem.setActivityId(activity.getActivityId().toString());
        activityItem.setActivityInfo(activity.getActivityInfo());
        activityItem.setActivityName(activity.getActivityName());
        activityItem.setActivityType(ActivityType.getNameByValue(activity.getActivityType()));
        activityItem.setAddress(activity.getAddress());
        activityItem.setBeginTime(convert(activity.getBeginTime()));
        activityItem.setCost(activity.getCost().toString());
        activityItem.setEndTime(convert(activity.getEndTime()));
        activityItem.setEnrollNumber(activity.getEnrollNumber().toString());
        activityItem.setImageUrl(activity.getActivityPhoto());
        activityItem.setSellerId(activity.getSellerId().toString());
        activityItem.setActivityInfo(activity.getActivityInfo());
        activityItem.setTotalNumber(activity.getTotalNumber().toString());
        return activityItem;
    }


    public String convert(Date d1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(d1);
    }


    public void updateEnrollNumber(ActivityEntity activityEntity) {
        activityDao.updateEnrollNumber(activityEntity);
    }


}
