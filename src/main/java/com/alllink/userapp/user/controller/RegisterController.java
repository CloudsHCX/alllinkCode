package com.alllink.userapp.user.controller;

import com.alllink.commons.exception.MapMessage;
import com.alllink.commons.utils.SHAUtil;
import com.alllink.userapp.user.entity.User;
import com.alllink.userapp.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/Register")
public class RegisterController {

    public static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param request
     * @param paramMap
     * @return
     */
    @RequestMapping(value="/toregistered",method = RequestMethod.POST)
    @ResponseBody
    public Object registered(HttpServletRequest request, @RequestBody Map<String, Object> paramMap){

        Map<String, Object> map=new HashMap<String, Object>();

        List<User> list  = null;
        User user =null;
        String checkcode = null;
        String phoneNumber = null;
        String password = null;


        if(paramMap.get("checkcode")!=null){
            checkcode = paramMap.get("checkcode").toString(); //获得传进来的验证码
        }
        if(paramMap.get("phoneNumber")!=null){
            phoneNumber = paramMap.get("phoneNumber").toString(); //获得传进来的电话号码
        }
        if(paramMap.get("password")!=null){
            password = paramMap.get("password").toString();
        }
        if(checkcode==null||phoneNumber==null||password==null){
            map = MapMessage.returnMapMessage(map,"error",
                    "传入的参数不对！","");
            return  map ;
        }

        //因为发短信之前就进行了用户的查询，注册的时候就不在进行查询-------但是不严谨
        //查询出数据库中存在待注册的数据
        paramMap.put("state",0);//状态为待注册的
        paramMap.remove("password");//移除password 参数
        int count_wait_login= 0;
        try{
            count_wait_login = userService.getListCount(paramMap); //查询未注册但是已经发过短信的
        }catch(Exception e){

            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户出错！",e.getMessage());
            return  map ;
        }

        if(count_wait_login == 0){

            map = MapMessage.returnMapMessage(map,"error",
                    "用户已经注册！","");
            return  map ;
        }

        try{
            list = userService.getList(paramMap);
        }catch(Exception e){

            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户数据失败2！","");
            return  map ;
        }

        user = list.get(0);

        String checkcode2 = String.valueOf(user.getIdentifyingCode());
        if(!checkcode.equals(checkcode2)){
            map = MapMessage.returnMapMessage(map,"error",
                    "验证码输入错误，请重新输入！","");
            return  map ;

        }
        String code_creat_time = user.getCodeCreatTime();

        //----------------------------------------------------------
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
        Date date = null;

        try{
            date = format.parse(code_creat_time);
        }catch(Exception e){
            map = MapMessage.returnMapMessage(map,"error",
                    "日期格式转换失败！",e.getMessage());
            e.printStackTrace();
            return  map ;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 10);// 10分钟
        date = cal.getTime();  //此时为截止时间
        Date nowdate =new Date();

        if(date.before(nowdate)){  //如果验证码的截止时间在当前时间之后 或者相等

            map = MapMessage.returnMapMessage(map,"error",
                    "验证码已经过期，请重新获得验证码！","");
            return  map ;
        }

        //验证码没有过期
        //加密密码
//        String  encryptPassword = SHAUtil.SHAEncode(password);
//        user.setPassword(encryptPassword);
        user.setPassword(password);
        user.setState(1);//已经注册；
        user.setModifiedTime(format.format(new Date()));
        try{
            userService.update(user);
        }catch(Exception e){

            map = MapMessage.returnMapMessage(map,"error",
                    "注册用户失败！",e.getMessage());
            e.printStackTrace();
            return  map ;

        }


        //创建session对象
        HttpSession session = request.getSession();
        //把用户数据保存在session域对象中
        session.setAttribute("phoneNumber", paramMap.get("phoneNumber").toString());
        //设置session 过期时间 ---以秒为单位的
        session.setMaxInactiveInterval(20*60);

        map = MapMessage.returnMapMessage(map,"success",
                "注册用户成功，即将跳转到首页面！","");
        return map;
    }

}
