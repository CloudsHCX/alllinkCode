package com.alllink.userapp.activity.entity;

import java.util.Comparator;

public class ActivityComparator implements Comparator<ActivityEntity> {
    private int sortParam;

    public int getSortParam() {
        return sortParam;
    }

    public void setSortParam(int sortParam) {
        this.sortParam = sortParam;
    }
    public ActivityComparator(int sortParam){
        this.sortParam = sortParam;
    }

    @Override
    public int compare(ActivityEntity o1, ActivityEntity o2) {
        switch (this.sortParam){
            case 0:
            case 1:
                if (o1.getDistance()>o2.getDistance()){
                    return 1;
                }else{
                    return -1;
                }
            case 2:
                if (o1.getCost()>o2.getCost()){
                    return 1;
                }else{
                    return -1;
                }
            case 3:
                if (o1.getCost()>o2.getCost()){
                    return -1;
                }else{
                    return 1;
                }
            default: return 0;
        }

    }

}
