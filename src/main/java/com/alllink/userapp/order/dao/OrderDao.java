package com.alllink.userapp.order.dao;


import com.alllink.userapp.order.entity.OrderEntity;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-06 17:42:52
 */
public interface OrderDao{
	void createOrder(OrderEntity order);
	List<OrderEntity> getNoPayOrderList(Integer userId);
	void deleteOrder(Integer orderId);
}
