package com.alllink.sellerapp.seller.service;


import com.alllink.sellerapp.seller.entity.SellerActivityEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-12-04 14:01:42
 */
public interface SellerActivityService {

	HashMap<String, Object> queryObject(int activityId);
	
	List<SellerActivityEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HashMap<String, Object> map);
	
	void update(HashMap<String, Object> map);
	
	void delete(int activityId);
	
	void deleteBatch(int[] activityIds);

    void updateTotalCost(int sellerId);
}
