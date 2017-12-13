package com.alllink.userapp.user.controller;

import com.alllink.commons.exception.MapMessage;
import com.alllink.commons.utils.SHAUtil;
import com.alllink.userapp.user.entity.User;
import com.alllink.userapp.user.service.UserService;
import com.alllink.userapp.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/Login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/tologin",method = RequestMethod.POST)
    @ResponseBody
    public Object Login(HttpServletRequest request, @RequestBody Map<String, Object> paramMap ){
        Map<String, Object> map=new HashMap<String, Object>();
        //Map<String, Object> paramMap=new HashMap<String, Object>();
        List<User> list  = null;
        User user = null ;
        int count = 0;

      if(paramMap.get("phoneNumber")==null){
            map = MapMessage.returnMapMessage(map,"error",
                    "传入的参数不对！","");
            return map;
        }
        if(paramMap.get("password")==null){


            map = MapMessage.returnMapMessage(map,"error",
                    "传入的参数不对2！","");
            return map;
        }

        //加密密码
        //String  encryptPassword = SHAUtil.SHAEncode(password);
        //查询---用户是否存在
        paramMap.put("state",1);

        //paramMap.put("password",encryptPassword);
        try{
            count = userService.getListCount(paramMap);

        }catch(Exception e){

            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户失败！",e.getMessage());
            return map;
        }
        if(count == 0){  //用户不存在

            map = MapMessage.returnMapMessage(map,"error",
                    "用户名或密码错误！","");
            return map;
        }

        if(count != 0){
            //创建session对象
            HttpSession session = request.getSession();
            //把用户数据保存在session域对象中
            //session.setAttribute("phoneNumber", paramMap.get("phoneNumber").toString());//lc改
            //lc 写
            User u = userService.getUserInfo(paramMap.get("phoneNumber").toString());
            session.setAttribute("user",u.getUserId());
            //lc结束
            //设置session 过期时间 ---以秒为单位的
            session.setMaxInactiveInterval(20*60);

            map = MapMessage.returnMapMessage(map,"success",
                    "登录成功,即将跳转首页！","");

        }
        //保存session


        return map;
    }

    /**
     * 登出 :是要使session无效还是清除某一个属性？？？
     * @param request
     * @return
     */
    @RequestMapping(value="/loginout",method = RequestMethod.POST)
    @ResponseBody
    public Object LoginOut(HttpServletRequest request ){
     Map<String,Object> map = new HashMap<String,Object>();

        //销毁session
        request.getSession().invalidate();

        map = MapMessage.returnMapMessage(map,"success",
                "退出成功","");


     return map;
    }
}
