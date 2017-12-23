package com.alllink.sellerapp.seller.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 基础Dao(还需在XML文件里，有对应的SQL语句)
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:31:36
 */
public interface BaseDao<T> {
	
	void save(T t);
	
	void save(HashMap<String, Object> map);
	
	void saveBatch(List<T> list);
	
	int update(T t);
	
	int update(HashMap<String, Object> map);
	
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	int deleteBatch(int[] id);

	HashMap<String, Object> queryObject(int id);
	
	List<T> queryList(Map<String, Object> map);
	
	List<T> queryList(int id);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal();
}
