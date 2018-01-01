package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.PageUtils;
import com.alllink.commons.utils.Query;
import com.alllink.commons.utils.R;
import com.alllink.sellerapp.seller.entity.ActivityOrderEntity;
import com.alllink.sellerapp.seller.entity.SellerActivityEntity;
import com.alllink.sellerapp.seller.service.ActivityOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/activityOrder")
public class ActivityOrderController {
    @Autowired
    private ActivityOrderService activityOrderService;

    /**
     * 列表分页查询
     */
    @RequestMapping("/pageList")
    @ResponseBody
    public R pageList(@RequestBody HashMap<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        System.out.println("list");
        List<ActivityOrderEntity> activityList = activityOrderService.queryList(query);
        int total = activityOrderService.queryTotal(query);
        System.out.println("====total" + total + "====limit:" + query.getLimit() + "=====page:" + query.getPage());
        PageUtils pageUtil = new PageUtils(activityList, total, query.getLimit(), query.getPage());
//        for(int i = 0; i < pageUtil.getPageSize(); i++){
//            System.out.println(pageUtil.getList().get(0));
//        }
        HashMap<String, Object> pageMap = new HashMap<>();
        pageMap.put("totalCount", total);
        pageMap.put("limit", query.getLimit());
        pageMap.put("currPage", query.getPage());
        pageMap.put("totalPage", pageUtil.getTotalPage());
        pageMap.put("list", activityList);
        return R.ok(pageMap);
    }

    /*
    *查询所有商家相关活动的订单
    * */
    @RequestMapping(value = "/queryAll", method = RequestMethod.POST)
    @ResponseBody
    public R queryAll(@RequestBody HashMap<String, Object> map) {
        List<ActivityOrderEntity> list = activityOrderService.queryList(map);
        return R.ok().put("list", list);
    }
}
