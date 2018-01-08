package com.alllink.userapp.order.service;

import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-06 17:42:52
 */
public interface OrderService {
	void CreateOrder(Integer userId, Integer sellerId, Integer activityId, String ord_str);

	List<OrderItem> getOrderList(OrderEntity orderEntity);

	void modifyOrder(OrderEntity orderEntity);

	List<OrderEntity> getList(Map<String, Object> map);
}
