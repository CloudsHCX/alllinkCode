package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.CheckDevice;
import com.alllink.commons.utils.R;
import com.alllink.commons.utils.RandomNumberUtil;
import com.alllink.commons.utils.TimeUtil;
import com.alllink.sellerapp.seller.entity.SellerAuthinfoEntity;
import com.alllink.sellerapp.seller.entity.SellerEntity;
import com.alllink.sellerapp.seller.service.SellerAuthinfoService;
import com.alllink.sellerapp.seller.service.SellerService;
import com.sun.tools.javac.comp.Check;
import javafx.beans.binding.ObjectExpression;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author xzz
 * @date 2017-12-08 19:46:48
 */
@RestController
@RequestMapping("sellerauthinfo")
public class SellerAuthinfoController {
    @Autowired
    private SellerAuthinfoService sellerAuthinfoService;
    @Autowired
    private SellerService sellerService;
    /**
     * 信息
     */
    @RequestMapping("/info")
    @ResponseBody
    public R info(@RequestBody HashMap<String, String> map) {
        System.out.println("SellerAuthinfoController  info :::"+map.get("sellerId"));
        HashMap<String, Object> activityMap = sellerAuthinfoService.queryObject(Integer.parseInt(map.get("sellerId")) );
        if(activityMap ==null){
            return R.error();
        }
        return R.ok().put("sellerAuthinfo", activityMap);
    }

    /**
     * 保存(添加商家法人信息)
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody SellerAuthinfoEntity sellerAuthinfo, HttpServletRequest request) throws ParseException {
        System.out.println(">>>>>>>>>>>>>>>>.add.. sellerauthinfo"+ sellerAuthinfo );
        sellerAuthinfo.setAuditState(0);
        sellerAuthinfo.setCreateTime(TimeUtil.getCurrentTime());
        sellerAuthinfoService.save(sellerAuthinfo);

        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setSellerId(sellerAuthinfo.getSellerId());
        sellerEntity.setState(2);
        sellerService.update(sellerEntity);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public R update(@RequestBody SellerAuthinfoEntity sellerAuthinfo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        SellerAuthinfoEntity sessionSeller = (SellerAuthinfoEntity) session.getAttribute("sellerAuthinfo");
        sellerAuthinfo.setSellerId(sessionSeller.getSellerId());

        sellerAuthinfo.setAuditState(0);

        sellerAuthinfoService.update(sellerAuthinfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @ResponseBody
    public R delete(@RequestBody Integer sauthinfoId) {
        sellerAuthinfoService.delete(sauthinfoId);

        return R.ok();
    }

}
