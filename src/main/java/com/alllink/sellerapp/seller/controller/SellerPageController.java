package com.alllink.sellerapp.seller.controller;

import com.alibaba.fastjson.parser.SymbolTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
* 请求地址映射，
* */
@Controller
@RequestMapping(value = "/views")
public class SellerPageController {

    @RequestMapping("/sellerapp/{page}")
    public ModelAndView showpage1(@PathVariable String page, ModelAndView mv) {
        mv.setViewName("sellerapp/"+page);
        mv.addObject("name", "hello world");
        return mv;
    }
    @RequestMapping("/sellerapp/modules/{page}")
    public ModelAndView showpage2(@PathVariable String page,ModelAndView mv) {
        mv.setViewName("sellerapp/modules/"+page);
        return mv;
    }
    @RequestMapping("/wechat/{page}")
    public ModelAndView showpage3(@PathVariable String page,ModelAndView mv) {
        mv.setViewName("wechat/"+page);
        return mv;
    }

    @RequestMapping("/wechatUser/{page}")
    public ModelAndView showpage4(@PathVariable String page, ModelAndView mv) {
        mv.setViewName("/wechatUser/" + page);
        return mv;
    }
}
