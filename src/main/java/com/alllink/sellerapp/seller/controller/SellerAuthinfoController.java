package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.R;
import com.alllink.sellerapp.seller.entity.SellerAuthinfoEntity;
import com.alllink.sellerapp.seller.service.SellerAuthinfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author xzz
 * @date 2017-12-08 19:46:48
 */
@RestController
@RequestMapping("sellerauthinfo")
public class SellerAuthinfoController {
    @Autowired
    private SellerAuthinfoService sellerAuthinfoService;

    /**
     * 信息
     */
    @RequestMapping("/info/{sauthinfoId}")
    @ResponseBody
    public R info(@PathVariable("sauthinfoId") Integer sauthinfoId) {
        SellerAuthinfoEntity sellerAuthinfo = sellerAuthinfoService.queryObject(sauthinfoId);
        //AuditState=-1代表未通过审核，0代表待审核状态，1代表审核通过状态
        if (sellerAuthinfo.getAuditState() == -1) {
            return R.error(0,"您提交的信息审核未通过");
        }else if (sellerAuthinfo.getAuditState() == 1) {
            return R.ok().put("sellerAuthinfo", sellerAuthinfo);
        }else {
            return R.error(0, "您提交的信息正在审核，请稍后查看");
        }
    }

    /**
     * 保存(添加商家法人信息)
     */
    @RequestMapping("/save")
    @ResponseBody
    public R save(@RequestBody SellerAuthinfoEntity sellerAuthinfo, HttpServletRequest request) {
        System.out.println("add.. sellerauthinfo" );

        HttpSession session = request.getSession();
        SellerAuthinfoEntity sessionSeller = (SellerAuthinfoEntity) session.getAttribute("sellerAuthinfo");
        sellerAuthinfo.setSellerId(sessionSeller.getSellerId());

        sellerAuthinfo.setAuditState(0);

        sellerAuthinfoService.save(sellerAuthinfo);

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
