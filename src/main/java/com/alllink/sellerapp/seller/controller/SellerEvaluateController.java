package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.PageUtils;
import com.alllink.commons.utils.Query;
import com.alllink.commons.utils.R;
import com.alllink.sellerapp.seller.entity.SellerActivityEntity;
import com.alllink.sellerapp.seller.entity.SellerEvaluateEntity;
import com.alllink.sellerapp.seller.service.SellerEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sellerEvaluate")
public class SellerEvaluateController {
    @Autowired
    private SellerEvaluateService sellerEvaluateService;

    @RequestMapping("/evaluateLevel")
    @ResponseBody
    public R evaluateLevel(@RequestBody HashMap<String, String> map) {
        int sellerId = Integer.parseInt(map.get("sellerId"));
        SellerEvaluateEntity sellerEvaluateEntity = sellerEvaluateService.findLevelBySellerId(sellerId);
        return R.ok().put("evaluate", sellerEvaluateEntity);
    }

    @RequestMapping("/pageEvaluate")
    @ResponseBody
    public R pageEvaluate(@RequestBody HashMap<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
//        System.out.println(params.get("criteria")+">>>>>>>>>"+query.get("criteria"));
        List<SellerEvaluateEntity> evaluateList = sellerEvaluateService.queryList(query);
        int total = sellerEvaluateService.queryTotal(query);
        PageUtils pageUtil = new PageUtils(evaluateList, total, query.getLimit(), query.getPage());
        HashMap<String, Object> pageMap = new HashMap<>();
        pageMap.put("totalCount", total);
        pageMap.put("limit", query.getLimit());
        pageMap.put("currPage", query.getPage());
        pageMap.put("totalPage", pageUtil.getTotalPage());
        pageMap.put("list", evaluateList);
        return R.ok(pageMap);
    }

    @RequestMapping("/evaluate")
    @ResponseBody
    public R evaluate(@RequestBody HashMap<String, Object> map) {
        System.out.println(map.get("sellerId"));
        List<SellerEvaluateEntity> evaluateList = sellerEvaluateService.queryList(map);

        return R.ok().put("evaluate", evaluateList);
    }
}
