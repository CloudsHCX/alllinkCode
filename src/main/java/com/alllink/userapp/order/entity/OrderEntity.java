package com.alllink.userapp.order.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-06 17:42:52
 */
public class OrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//订单id
	private Integer orderId;
	//用户id
	private Integer userId;
	//商家id
	private Integer sellerId;
	//活动id
	private Integer activityId;
	//订单状态
	private Integer orderState;
	//金额
	private Double cost;
	//活动名称
	private String activityName;
	//订单创建时间
	private Date createTime;
	//订单修改时间
	private Date modifiedTime;
	private int evaluateState;

	/**
	 * 设置：订单id
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：商家id
	 */
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	/**
	 * 获取：商家id
	 */
	public Integer getSellerId() {
		return sellerId;
	}
	/**
	 * 设置：活动id
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取：活动id
	 */
	public Integer getActivityId() {
		return activityId;
	}
	/**
	 * 设置：订单状态
	 */
	public void setOrderState(Integer orderState) {
		this.orderState = orderState;
	}
	/**
	 * 获取：订单状态
	 */
	public Integer getOrderState() {
		return orderState;
	}
	/**
	 * 设置：金额
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}
	/**
	 * 获取：金额
	 */
	public Double getCost() {
		return cost;
	}
	/**
	 * 设置：活动名称
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * 获取：活动名称
	 */
	public String getActivityName() {
		return activityName;
	}
	/**
	 * 设置：订单创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：订单创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：订单修改时间
	 */
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	/**
	 * 获取：订单修改时间
	 */
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public int getEvaluateState() {
		return evaluateState;
	}

	public void setEvaluateState(int evaluateState) {
		this.evaluateState = evaluateState;
	}
}
