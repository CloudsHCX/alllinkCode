package com.alllink.userapp.order.service.impl;

import com.alllink.userapp.activity.dao.ActivityDao;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.order.dao.OrderDao;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.entity.OrderItem;
import com.alllink.userapp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ActivityDao activityDao;


	@Override
	public void CreateOrder(Integer userId, Integer sellerId, Integer activityId,String ord_str) {
		OrderEntity order = new OrderEntity();
		order.setUserId(userId);
		order.setSellerId(sellerId);
		order.setActivityId(activityId);
		ActivityEntity activityEntity = activityDao.getActivityInfo(activityId);
		order.setActivityName(activityEntity.getActivityName());
		order.setCost(activityEntity.getCost());
		order.setCreateTime(new Date());
		order.setOrderState(0);
		order.setModifiedTime(new Date());
		order.setEvaluateState(0);
		order.setOrderIdStr(ord_str);
		orderDao.createOrder(order);
	}

	@Override
	public List<OrderItem> getOrderList(OrderEntity orderEntity) {
		List<OrderEntity> orders =  orderDao.getOrderList(orderEntity);
		List<OrderItem> orderList = new ArrayList<>();
		for (OrderEntity order:orders){
			OrderItem item = new OrderItem();
			item.setOrderIdStr(order.getOrderIdStr());
			item.setActivityName(order.getActivityName());
			item.setCost(order.getCost().toString());
			item.setCreateTime(convert(order.getCreateTime()));
			item.setOrderId(order.getOrderId().toString());
			ActivityEntity activity = activityDao.getActivityInfo(order.getActivityId());
			item.setImageUrl(activity.getActivityPhoto().split(",")[0]);
			item.setOrderState(order.getOrderState().toString());
			orderList.add(item);
		}
		return orderList;
	}

	@Override
	public void modifyOrder(OrderEntity orderEntity) {
		orderDao.modifyOrder(orderEntity);
	}

	@Override
	public List<OrderEntity> getList(Map<String, Object> map) {
		return orderDao.getList(map);
	}

	public String convert(Date d1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d1);
	}
}
