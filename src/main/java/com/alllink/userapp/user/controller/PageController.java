package com.alllink.userapp.user.controller;


import com.alllink.commons.enums.ActivityState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/userapp")
public class PageController {
    //合并

     @RequestMapping("/{page}")
     public ModelAndView showpage1(@PathVariable String page,ModelAndView mv) {
         mv.setViewName("userapp/"+page);
        return mv;
      }
    @RequestMapping("/personal_center/{page}")
    public ModelAndView showpage2(@PathVariable String page,ModelAndView mv) {
        mv.setViewName("userapp/personal_center/"+page);
        return mv;
    }


}
