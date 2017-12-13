package com.alllink.userapp.order.controller;

import com.alllink.commons.utils.CheckDevice;
import com.alllink.commons.utils.JsonUtils;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.entity.OrderItem;
import com.alllink.userapp.order.service.OrderService;
import com.alllink.userapp.user.entity.User;
import org.apache.shiro.session.Session;
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
@RequestMapping(value="/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "toNoPayList")
    public ModelAndView toActivityList(ModelAndView mv){
        /*return "redirect:/userapp/personal_center/nearby";*/
        mv.setViewName("userapp/personal_center/order/unpaid_order");

        return mv;
    }



    @RequestMapping(value = "/apply",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String createOrder(@RequestBody OrderEntity order, HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        try{
            String userID = null;//获取用户Id
            if (CheckDevice.getDevice(request)==1){
                userID = order.getUserId().toString();
            }else {
                if (session.getAttribute("user")==null){
                    userID = null;
                }else {
                    userID = session.getAttribute("user").toString();
                }
            }
            if (userID==null||userID.trim().length()==0){
                return "0";//表示未登陆，跳转到登陆页面
            }
            orderService.CreateOrder(Integer.parseInt(userID),order.getSellerId(),order.getActivityId());
            map.put("result","success");
            map.put("message","活动报名成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("result","error");
            map.put("message","活动报名失败");
        }
        map.put("data","");
        map.put("exception","");
        return JsonUtils.objectToJson(map);

    }
    @RequestMapping(value = "/getNonPayList",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getNoPayOrder(@RequestBody User user,HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        String userID = null;//获取用户Id
        if (CheckDevice.getDevice(request)==1){
            userID = user.getUserId().toString();
        }else {
            userID = session.getAttribute("user").toString();
        }
        List<OrderItem> orderList = orderService.getNoPayOrderList(Integer.parseInt(userID));
        if (orderList!=null&&orderList.size()>0){
            map.put("result","success");
            map.put("message","获取未付款订单列表成功");

        }else {
            map.put("result","error");
            map.put("message","获取未付款订单列表失败");
        }
        map.put("data",orderList);
        map.put("exception","");
        String str = JsonUtils.objectToJson(map);
        return str;
    }
    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String deleteOrder(@RequestBody OrderEntity order){
        Map<String,Object> map = new HashMap<>();
        try {
            orderService.deleteOrder(order.getOrderId());
            map.put("result","success");
            map.put("message","删除订单成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("result","error");
            map.put("message","删除订单失败");
        }
        map.put("data","");
        map.put("exception","");
        return JsonUtils.objectToJson(map);
    }

}
