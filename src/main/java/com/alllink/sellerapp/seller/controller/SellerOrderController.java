package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.R;
import com.alllink.commons.utils.TimeUtil;
import com.alllink.sellerapp.seller.service.SellerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;

@Controller
@RequestMapping("/sellerOrder")
public class SellerOrderController {
    @Autowired
    private SellerOrderService sellerOrderService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public R save(@RequestBody HashMap<String, Object> map) throws ParseException {
        map.put("createTime", TimeUtil.getCurrentTime());
        sellerOrderService.save(map);
        return R.ok();
    }

    @RequestMapping(value = "/pageList", method = RequestMethod.POST)
    @ResponseBody
    public R pageList(@RequestBody HashMap<String, Object> map) throws ParseException {
        map.put("createTime", TimeUtil.getCurrentTime());
        sellerOrderService.save(map);
        return R.ok();
    }

}
