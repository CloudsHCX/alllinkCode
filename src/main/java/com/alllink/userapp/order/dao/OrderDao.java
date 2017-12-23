package com.alllink.userapp.order.dao;


import com.alllink.userapp.order.entity.OrderEntity;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-06 17:42:52
 */
public interface OrderDao{
	void createOrder(OrderEntity order);
	List<OrderEntity> getOrderList(OrderEntity orderEntity);
	void modifyOrder(OrderEntity orderEntity);

	List<OrderEntity> getList(Map<String, Object> map);
}
