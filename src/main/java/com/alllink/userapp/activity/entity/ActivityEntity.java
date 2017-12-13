package com.alllink.userapp.activity.entity;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-04 14:01:42
 */
public class ActivityEntity{
	private static final long serialVersionUID = 1L;
	
	//活动id
	private Integer activityId;
	//商家id
	private Integer sellerId;
	//经度
	private double longitude;
	//纬度
	private double latitude;
	//活动名称
	private String activityName;
	//总人数
	private Integer totalNumber;
	//费用
	private Double cost;
	//活动照片  放文件夹
	private String activityPhoto;
	//活动类型
	private Integer activityType;
	//活动开始时间
	private Date beginTime;
	//活动结束时间
	private Date endTime;
	//已报名人数
	private Integer enrollNumber;
	//活动信息
	private String activityInfo;
	//活动状态
	private Integer activityState;
	//活动创建时间
	private Date createTime;
	//活动修改时间
	private Timestamp modifiedTime;
	//活动审核时间
	private Date auditTime;
	//活动地址
	private String address;
	//活动和用户所在地之间的距离
	private Double distance;

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public void setActivityPhoto(String activityPhoto) {
		this.activityPhoto = activityPhoto;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setEnrollNumber(Integer enrollNumber) {
		this.enrollNumber = enrollNumber;
	}

	public void setActivityInfo(String activityInfo) {
		this.activityInfo = activityInfo;
	}

	public void setActivityState(Integer activityState) {
		this.activityState = activityState;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public String getActivityName() {
		return activityName;
	}

	public Integer getTotalNumber() {
		return totalNumber;
	}

	public Double getCost() {
		return cost;
	}

	public String getActivityPhoto() {
		return activityPhoto;
	}

	public Integer getActivityType() {
		return activityType;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Integer getEnrollNumber() {
		return enrollNumber;
	}

	public String getActivityInfo() {
		return activityInfo;
	}

	public Integer getActivityState() {
		return activityState;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public String getAddress() {
		return address;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}


	@Override
	public String toString() {
		return "ActivityEntity{" +
				"activityId=" + activityId +
				", sellerId=" + sellerId +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", activityName='" + activityName + '\'' +
				", totalNumber=" + totalNumber +
				", cost=" + cost +
				", activityPhoto='" + activityPhoto + '\'' +
				", activityType=" + activityType +
				", beginTime=" + beginTime +
				", endTime=" + endTime +
				", enrollMumber=" + enrollNumber +
				", activityInfo='" + activityInfo + '\'' +
				", activityState=" + activityState +
				", createTime=" + createTime +
				", modifiedTime=" + modifiedTime +
				", auditTime=" + auditTime +
				", address='" + address + '\'' +
				'}';
	}


}
