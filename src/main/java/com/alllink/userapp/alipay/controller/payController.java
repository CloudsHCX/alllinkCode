package com.alllink.userapp.alipay.controller;

import com.alllink.commons.enums.OrderState;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/pay")
public class payController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/refundCheckWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object refundCheckWeb(@RequestBody Map<String, Object> paramMap) {
        Map<String, Object> map = new HashMap<String, Object>();

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderIdStr(paramMap.get("orderIdStr").toString());
        orderEntity.setOrderState(OrderState.OrderRefundCheck.getValue());
        try {
            orderService.modifyOrder(orderEntity);
        } catch (Exception e) {

            map.put("result", "error");
            return map;
        }
        map.put("result", "success");
        return map;

    }

}
