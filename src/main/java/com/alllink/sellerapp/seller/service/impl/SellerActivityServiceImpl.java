package com.alllink.sellerapp.seller.service.impl;

import com.alllink.sellerapp.seller.dao.SellerActivityDao;
import com.alllink.sellerapp.seller.entity.SellerActivityEntity;
import com.alllink.sellerapp.seller.service.SellerActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("activityService")
public class SellerActivityServiceImpl implements SellerActivityService {
	@Autowired
	private SellerActivityDao activityDao;
	
	@Override
	@Transactional
	public HashMap<String, Object> queryObject(int activityId){
		return activityDao.queryObject(activityId);
	}
	
	@Override
	@Transactional
	public List<SellerActivityEntity> queryList(Map<String, Object> map){
		return activityDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return activityDao.queryTotal(map);
	}
	
	@Override
	public void save(HashMap<String, Object> map){
		activityDao.save(map);
	}
	
	@Override
	@Transactional
	public void update(HashMap<String, Object> map){
		activityDao.update(map);
	}
	
	@Override
	@Transactional
	public void delete(int activityId){
		activityDao.delete(activityId);
	}
	
	@Override
	public void deleteBatch(int[] activityIds){
		activityDao.deleteBatch(activityIds);
	}
	
}
