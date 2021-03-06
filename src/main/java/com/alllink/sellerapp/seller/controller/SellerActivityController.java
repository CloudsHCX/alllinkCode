package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.enums.ActivityType;
import com.alllink.commons.utils.*;
import com.alllink.sellerapp.seller.entity.SellerActivityEntity;
import com.alllink.sellerapp.seller.entity.SellerEntity;
import com.alllink.sellerapp.seller.service.SellerActivityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/sellerActivity")
public class SellerActivityController {
	@Autowired
	private SellerActivityService activityService;

	/**
	 * 列表分页查询
	 */
    @RequestMapping("/pageList")
    @ResponseBody
    public R pageList(@RequestBody HashMap<String, Object> params) {
        System.out.println(">>>>>>>>>>>>>" + params.get("page").toString() + params.get("limit").toString());
        //查询列表数据
		Query query = new Query(params);

		List<SellerActivityEntity> activityList = activityService.queryList(query);
		int total = activityService.queryTotal(query);
		PageUtils pageUtil = new PageUtils(activityList, total, query.getLimit(), query.getPage());
//		for(int i = 0; i < pageUtil.getPageSize(); i++){
//			System.out.println(pageUtil.getList().get(0));
//		}
        HashMap<String, Object> pageMap = new HashMap<>();
        pageMap.put("totalCount", total);
        pageMap.put("limit", query.getLimit());
        pageMap.put("currPage", query.getPage());
        pageMap.put("totalPage", pageUtil.getTotalPage());
        pageMap.put("list", activityList);
        return R.ok(pageMap);
    }

	@RequestMapping("/queryAll")
	@ResponseBody
	public R queryAll(@RequestBody HashMap<String, Object> map){
		System.out.println(map.get("sellerId"));
		List<SellerActivityEntity> list = activityService.queryList(map);

//		for(int i = 0 ; i < list.size() ; i++) {
//			list.get(i).setBeginTime(TimeUtil.timestampToString(list.get(i).getBeginTime()));
//		}
//		return JsonUtils.objectToJson(R.ok().put("list", list));
		return R.ok().put("list", list);
	}

	/**
	 * 查看活动信息
	 */
	@RequestMapping("/info")
	@ResponseBody
	public R info(@RequestBody  HashMap<String, String> map){
		System.out.println(">>>>>>>>>>>>>" + map.get("activityId"));
		HashMap<String, Object> activityMap = activityService.queryObject(Integer.parseInt(map.get("activityId")));
		System.out.println(activityMap.get("beginTime"));
		return R.ok().put("activity", activityMap);
	}

	/**
	 * 保存(添加活动)
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public R save(@RequestBody HashMap<String, Object> map, HttpServletRequest request) throws ParseException {

		//校验添加活动的活动名称
	/*	if(activityName==null ||activityName.trim()==""){
			return R.error("活动名称不能为空");
		}*/

//		String beginTime = (String)map.get("beginTime");
//		String endTime = (String)map.get("endTime");
//		map.put("beginTime", Timestamp.valueOf(beginTime));
//		map.put("endTime", Timestamp.valueOf(endTime));
//		map.put("createTime", TimeUtil.getCurrentTime());
	/*	if(CheckDevice.getDevice(request) == 0){
			HttpSession session = request.getSession();
			SellerEntity sessionSeller = (SellerEntity) session.getAttribute("seller");
			map.put("sellerId", 1);//sessionSeller.getSellerId());
		}*/
		Timestamp createTime = TimeUtil.getCurrentTime();
		map.put("createTime", createTime);
        map.put("enrollNumber", 0);
        activityService.save(map);
		return R.ok();
	}

	/**
	 * 修改活动信息
	 */
	@RequestMapping(value="/update",method= RequestMethod.POST)
	@ResponseBody
	public R update(@RequestBody HashMap<String, Object> map, HttpServletRequest request) {
		System.out.println(">>>>>>>>activity:" + map.get("longitude")+map.get("latitude"));

//		if(CheckDevice.getDevice(request) == 0){
//			HttpSession session =request.getSession();
//			SellerEntity sessionSeller=(SellerEntity)session.getAttribute("seller");
//			map.put("sellerId",sessionSeller.getSellerId());
//		}

//		String beginTime = (String)map.get("beginTime");
//		String endTime = (String)map.get("endTime");
//		map.put("beginTime", Timestamp.valueOf(beginTime));
//		map.put("endTime", Timestamp.valueOf(endTime));
		activityService.update(map);



		return R.ok(map);
	}

	/**
	 * 删除活动
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestBody HashMap<String, Object> map){
		System.out.println(">>>>>>>>>>>>>");
		activityService.delete(Integer.parseInt(String.valueOf(map.get("activityId"))));


		return R.ok();
	}


	/**
	 * 批量删除活动
	 */
	/*
	@RequestMapping("/deleteBatch")
	@ResponseBody
	public R delete(@RequestBody int[] activityIds){
		activityService.deleteBatch(activityIds);

		return R.ok();
	}
	*/

}
