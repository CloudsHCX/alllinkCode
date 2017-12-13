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
@RequestMapping(value = "/sellerapp")
public class SellerPageController {

    @RequestMapping("/{page}")
    public ModelAndView showpage1(@PathVariable String page, ModelAndView mv) {
        mv.setViewName("sellerapp/"+page);
        System.out.println(mv.getViewName());
        return mv;
    }
    @RequestMapping("/modules/{page}")
    public ModelAndView showpage2(@PathVariable String page,ModelAndView mv) {
        mv.setViewName("sellerapp/modules/"+page);
        return mv;
    }
}
