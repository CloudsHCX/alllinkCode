package com.alllink.userapp.order.service;

import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.entity.OrderItem;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-06 17:42:52
 */
public interface OrderService {
	public void CreateOrder(Integer userId, Integer sellerId, Integer activityId);
	public List<OrderItem> getNoPayOrderList(Integer UserId);
	public void deleteOrder(Integer orderid);
}
