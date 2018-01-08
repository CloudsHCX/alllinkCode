package com.alllink.userapp.alipay.controller;

import com.alllink.commons.enums.OrderState;
import com.alllink.commons.enums.PaymentChannel;
import com.alllink.userapp.activity.entity.ActivityDetailItem;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.service.ActivityService;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.service.OrderService;
import com.alllink.userapp.user.entity.User;
import com.alllink.userapp.user.service.UserService;
import org.apache.shiro.web.session.HttpServletSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
public class payController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/refundCheck", method = RequestMethod.POST)
    @ResponseBody
    public Object refundCheckWeb(@RequestBody Map<String, Object> paramMap) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }
        if (paramMap.get("refundReason") == null) {
            map.put("result", "error");
            return map;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderIdStr(paramMap.get("orderIdStr").toString());
        orderEntity.setOrderState(OrderState.OrderRefundCheck.getValue());
        orderEntity.setRefundReason((paramMap.get("refundReason").toString()));
        try {
            orderService.modifyOrder(orderEntity);
        } catch (Exception e) {

            e.printStackTrace();
            map.put("result", "error");
            return map;
        }
        map.put("result", "success");
        return map;

    }


    @RequestMapping(value = "/updateOrderInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object updateOrderInfo(HttpServletRequest httpRequest, @RequestBody Map<String, Object> paramMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        //app 需要再传一个 userId

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }
        if (paramMap.get("totalAmount") == null) {
            map.put("result", "error");
            return map;
        }
        String orderIdStr = paramMap.get("orderIdStr").toString();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderIdStr(orderIdStr);
        orderEntity.setOrderState(OrderState.OrderPayed.getValue());
        orderEntity.setPaymentChannel(PaymentChannel.alipay.getValue());
        try {
            orderService.modifyOrder(orderEntity);
        } catch (Exception e) {

            map.put("result", "error");
            return map;
        }


        //付款成功后，活动人数减一

        //获取订单具体信息
        List<OrderEntity> list = null;
        list = orderService.getList(paramMap);
        if (list == null) {
            map.put("result", "error");
            return map;
        }

        int activityId = list.get(0).getActivityId();

        //获得活动信息
        ActivityDetailItem activityDetailItem = new ActivityDetailItem();
        activityDetailItem = activityService.getActivityInfo(activityId);

        //修改活动信息

        Integer enrollNumber = new Integer(activityDetailItem.getEnrollNumber());

        enrollNumber = enrollNumber + 1;

        ActivityEntity activityEntity = new ActivityEntity();
        activityEntity.setActivityId(activityId);
        activityEntity.setEnrollNumber(enrollNumber);

        try {
            activityService.updateEnrollNumber(activityEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //付款成功之后，用户的积分增加
        User user = new User();
        if (paramMap.get("userId") != null) {
            user.setUserId(Integer.parseInt(paramMap.get("userId").toString()));
        } else {
            user.setUserId(list.get(0).getUserId());
            httpRequest.getSession().setAttribute("user", list.get(0).getUserId());

        }

        user.setTotalPoints((int) Double.parseDouble(paramMap.get("totalAmount").toString()));

        try {
            userService.updatetotalPoints(user);
        } catch (Exception e) {

            e.printStackTrace();

        }

        map.put("result", "success");
        return map;
    }


    /**
     * 测试一下  ok
     */
    @RequestMapping(value = "/testUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Object testUpdate(@RequestBody User user) {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            userService.updatetotalPoints(user);
        } catch (Exception e) {

            e.printStackTrace();
            map.put("result", "error");
            return map;
        }

        map.put("result", "success");
        return map;
    }

}
