package com.alllink.userapp.order.controller;

import com.alllink.commons.enums.OrderState;
import com.alllink.commons.enums.PaymentChannel;
import com.alllink.commons.utils.CheckDevice;
import com.alllink.commons.utils.JsonUtils;
import com.alllink.userapp.activity.entity.ActivityDetailItem;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.entity.OrderItem;
import com.alllink.userapp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    SimpleDateFormat sdf = new SimpleDateFormat("MMdd");//获取月和日
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
        String orderAttach = null;//下单渠道
        String datetime = null;//时间信息
        String randomCode = null;//四位随机码
        String userIdString = "";//用户ID后四位；
        datetime = sdf.format(new Date());
        randomCode = (int)(((Math.random()*9)+1)*1000)+"";
        try{
            String userID = null;//获取用户Id
            if (CheckDevice.getDevice(request)==1){
                userID = order.getUserId().toString();
                orderAttach = "1";
            }else {
                orderAttach = "2";
                    userID = session.getAttribute("user").toString();
            }
            //取userid后四位
            if (userID.length()<4){
                int ll = 4-userID.length();
                for (int i = 0;i<ll;i++) {
                    userIdString+="0";

                }
                userIdString+=userID;
            }
            if (userID.length()==4){
                userIdString = userID;
            }
            if (userID.length()>4){
                userIdString = userID.substring(userID.length()-5,userID.length()-1);
            }
            order.setOrderIdStr(orderAttach+datetime+randomCode+userIdString);
            orderService.CreateOrder(Integer.parseInt(userID),order.getSellerId(),order.getActivityId(),order.getOrderIdStr());
            map.put("result","success");
            map.put("message","活动报名成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("result","error");
            map.put("message","活动报名失败");
        }
        Map<String,String> orderId = new HashMap<>();//这里是返回给安卓的订单号
        orderId.put("orderId",order.getOrderIdStr());
        map.put("data",orderId);
        map.put("exception","");
        return JsonUtils.objectToJson(map);

    }
    //获取订单 orderGet中的orderState表示要获取什么样的订单，如果不传表示获取全部订单，-1表示取消的订单，0表示未付款订单，1表示已经付款的订单
    @RequestMapping(value = "/getOrderList",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String getNoPayOrder(@RequestBody OrderEntity orderEntity,HttpServletRequest request, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        String userID = null;//获取用户Id
        if (CheckDevice.getDevice(request)==1){
            userID = orderEntity.getUserId().toString();
        }else {
            userID = session.getAttribute("user").toString();
        }
        orderEntity.setUserId(Integer.parseInt(userID));
        List<OrderItem> orderList = orderService.getOrderList(orderEntity);
            map.put("result","success");
            map.put("message","获取订单列表成功");
        map.put("data",orderList);
        map.put("exception","");
        String str = JsonUtils.objectToJson(map);
        return str;
    }

    //修改订单状态，删除则orderState传递-1，支付完成orderState传递1
    @RequestMapping(value = "/modifyOrderState",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String deleteOrder(@RequestBody OrderEntity order){
        Map<String,Object> map = new HashMap<>();
        try {
            orderService.modifyOrder(order);
            map.put("result","success");
            map.put("message","操作订单成功");
        }catch (Exception e){
            e.printStackTrace();
            map.put("result","error");
            map.put("message","操作订单失败");
        }
        map.put("data","");
        map.put("exception","");
        return JsonUtils.objectToJson(map);
    }


}
