package com.alllink.userapp.activity.controller;

import com.alllink.commons.utils.CheckDevice;
import com.alllink.commons.utils.JsonUtils;
import com.alllink.userapp.activity.entity.ActivityDetailItem;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.entity.ActivityItem;
import com.alllink.userapp.activity.entity.ActivitySearch;
import com.alllink.userapp.activity.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/activity")
public class ActivityController {
    private int actId;
    @Autowired
    private ActivityService activityService;

    /**获取默认的活动
     * 图片的地址还没有处理，需要和商家讨论
     * @return
     */
    @RequestMapping(value = "toActivityList")
    public ModelAndView toActivityList(ModelAndView mv){
        /*return "redirect:/userapp/personal_center/nearby";*/
        mv.setViewName("userapp/personal_center/nearby");

        return mv;
    }

    @RequestMapping(value = "setActivityId")
    public ModelAndView toActivityList(String activityId, HttpSession session,ModelAndView mv){
        actId = Integer.parseInt(activityId);
        mv.setViewName("/userapp/personal_center/activity_info");
        return mv;
    }

    @RequestMapping(value = "/getDefaultActivityList",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getDefaultAct(@RequestBody ActivitySearch activitySearch, HttpServletRequest request){
            Map<String,Object> map = new HashMap<>();

        List<ActivityItem> list = activityService.getDefaultActivity(activitySearch);
        if (list!=null&&list.size()>0){
            map.put("result","success");
            map.put("message","获取默认活动列表成功");

        }else {
            map.put("result","error");
            map.put("message","获取默认活动列表失败");

        }
        if (CheckDevice.getDevice(request)==1){
            for (ActivityItem activityItem :list){
                activityItem.setImageUrl(activityItem.getImageUrl());
            }
        }
        map.put("data",list);
        map.put("exception","");
        return JsonUtils.objectToJson(map);
    }


    @RequestMapping(value = "/getActivityList",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getActivityList(@RequestBody ActivitySearch activitySearch){
        Map<String,Object> map = new HashMap<>();

        List<ActivityItem> list = activityService.getActivityByDistance(activitySearch);
        if (list!=null&&list.size()>0){
            map.put("result","success");
            map.put("message","获取活动列表成功");

        }else {
            map.put("result","error");
            map.put("message","获取活动列表失败");

        }
        map.put("data",list);
        map.put("exception","");
        return JsonUtils.objectToJson(map);
    }
    @RequestMapping(value="getActivityInfo",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
       public String getActivityInfo(@RequestBody ActivityEntity activityEntity,HttpServletRequest request){

        int aa = actId;
        Map<String,Object> map = new HashMap<>();
        Integer activiityId = null;
        if (CheckDevice.getDevice(request)==1){
            activiityId = activityEntity.getActivityId();
        }else {
            //activiityId = Integer.parseInt(request.getSession().getAttribute("activityId").toString());
            activiityId = actId;
        }
        ActivityDetailItem activity =  activityService.getActivityInfo(activiityId);
        if (activity!=null){
            map.put("result","success");
            map.put("message","获取活动详情成功");

        }else {
            map.put("result","error");
            map.put("message","获取活动详情失败");

        }
        map.put("data",activity);
        map.put("exception","");
        return JsonUtils.objectToJson(map);
    }

}
