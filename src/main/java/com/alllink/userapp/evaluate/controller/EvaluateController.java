package com.alllink.userapp.evaluate.controller;


import com.alllink.commons.enums.OrderState;
import com.alllink.userapp.evaluate.entity.EvaluateEntity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByActivity;
import com.alllink.userapp.evaluate.entity.EvaluateEntityByOne;
import com.alllink.userapp.evaluate.service.EvaluateService;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.service.OrderService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/evaluate")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @Autowired
    private OrderService orderService;


    @RequestMapping(value = "/saveWeb")
    @ResponseBody
    public Object saveWeb(HttpServletRequest httpRequest, @RequestBody EvaluateEntity evaluateEntity) {

        Map<String, Object> map = new HashMap<String, Object>();

        //从 session 中获得获得 user_id
        if (httpRequest.getSession().getAttribute("user") == null) {
            map.put("result", "error");
            map.put("message", "添加评价失败");
            return map;
        }
        int userId = Integer.parseInt(httpRequest.getSession().getAttribute("user").toString());
        evaluateEntity.setUserId(userId);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制

        evaluateEntity.setCreateTime(format.format(new Date()));
        try {
            evaluateService.add(evaluateEntity);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "error");
            map.put("message", "添加评价失败");
            return map;
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(evaluateEntity.getOrderId());
        orderEntity.setOrderState(OrderState.OrderEvaluate.getValue());
        try {
            orderService.modifyOrder(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "error");
            map.put("message", "添加评价失败");
            return map;

        }

        map.put("result", "success");
        map.put("message", "添加评价成功");
        return map;
    }

    @RequestMapping(value = "/saveApp")
    @ResponseBody
    public Object saveApp(@RequestBody EvaluateEntity evaluateEntity) {

        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制

        evaluateEntity.setCreateTime(format.format(new Date()));

        try {
            evaluateService.add(evaluateEntity);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "添加评价失败");
            map.put("result", "error");
            return map;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(evaluateEntity.getOrderId());
        orderEntity.setOrderState(OrderState.OrderEvaluate.getValue());
        try {
            orderService.modifyOrder(orderEntity);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "添加评价失败");
            map.put("result", "error");
            return map;

        }

        map.put("message", "添加评价成功");
        map.put("result", "success");
        return map;
    }

    @RequestMapping(value = "/getListByOneWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object getListByOneWeb(HttpServletRequest httpRequest, @RequestBody EvaluateEntityByOne evaluateEntityByOne) {

        Map<String, Object> map = new HashMap<String, Object>();
        //从 session中获得 用户id
        int userId = Integer.parseInt(httpRequest.getSession().getAttribute("user").toString());
        evaluateEntityByOne.setUserId(userId);

        List<EvaluateEntityByOne> list = null;
        try {
            list = evaluateService.getListByOne(evaluateEntityByOne);
        } catch (Exception e) {
            e.printStackTrace();

            map.put("result", "error");
            map.put("message", "获取用户评价失败");
            return map;
        }

        map.put("list", list);
        map.put("message", "获取用户评价成功");
        map.put("result", "success");
        return map;
    }

    @RequestMapping(value = "/getListByOneApp", method = RequestMethod.POST)
    @ResponseBody
    public Object getListByOneApp(@RequestBody EvaluateEntityByOne evaluateEntityByOne) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<EvaluateEntityByOne> list = null;
        try {
            list = evaluateService.getListByOne(evaluateEntityByOne);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "获取用户评价失败");
            map.put("result", "error");
            return map;
        }

        map.put("list", list);
        map.put("message", "获取用户评价成功");
        map.put("result", "success");
        return map;
    }


    @RequestMapping(value = "/getListByActivity", method = RequestMethod.POST)
    @ResponseBody
    public Object getListByActivity(@RequestBody EvaluateEntityByActivity evaluateEntityByActivity) {

        Map<String, Object> map = new HashMap<String, Object>();

        List<EvaluateEntityByActivity> list = null;
        try {
            list = evaluateService.getListByActivity(evaluateEntityByActivity);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "获取活动评价失败");
            map.put("result", "error");
            return map;
        }

        map.put("list", list);
        map.put("message", "获取活动评价");
        map.put("result", "success");
        return map;
    }


}
