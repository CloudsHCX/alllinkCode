package com.alllink.userapp.user.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.alllink.commons.exception.MapMessage;
import com.alllink.commons.utils.CheckCodeUtil;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.alllink.commons.sms.smsdemo.sendSms;

@Controller
@RequestMapping(value = "/SendMessage")
public class SendMessageController {

    public static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 发送短信
     * @param request
     * @param paramMap ： phoneNumber, sign
     * @return
     * @throws ClientException
     * @throws Exception
     */
    @RequestMapping(value="/sms",method = RequestMethod.POST)
    @ResponseBody
    public Object sendMessage(HttpServletRequest request, @RequestBody Map<String, Object> paramMap) throws Exception {

        Map<String, Object> map=new HashMap<String, Object>();
        String phoneNumber = null;
        User user = new User();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
        List<User> list  = null;
        int sign = 0; // 0：注册发短信: 1：重置密码发短信
        //随机生成 num 位验证码
        String checkCode= CheckCodeUtil.generateCheckCode();


        if(paramMap.get("phoneNumber")!=null){
            phoneNumber = paramMap.get("phoneNumber").toString();
        }else{
            map = MapMessage.returnMapMessage(map,"error",
                    "传递的参数不含有手机号！","");
            return map;
        }

        if(paramMap.get("sign")!=null){
            sign =Integer.parseInt(paramMap.get("sign").toString());
        }else{
            map = MapMessage.returnMapMessage(map,"error",
                    "传递的参数不合法！","");
            return map;
        }

        if(phoneNumber==null || phoneNumber.length()!=11){  //判断手机号是否合法

            map = MapMessage.returnMapMessage(map,"error",
                    "传入的手机号不合法，重新输入！","");
            return map;
        }

        int count_done_login = 0; //已经注册的个数
        int count_wait_login = 0; //待注册的个数

        paramMap.put("state", 1); //注册状态为1
        try {

            count_done_login = userService.getListCount(paramMap); //查询已经注册过的用户
        }catch (Exception e){

            logger.error( "sendMessage查询用户出错", e );
            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户出错！",e.getMessage());
            e.printStackTrace();
            return map;
        }

        paramMap.put("state",0); //注册状态为0
        try{
            count_wait_login = userService.getListCount(paramMap); //查询未注册但是已经发过短信的
        }catch(Exception e){

            logger.error( "sendMessage查询用户出错", e );
            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户出错！",e.getMessage());
            e.printStackTrace();
            return map;
        }




        /******注册发送验证码********/
        if(sign == 0){ //注册发送验证码

            if(count_done_login != 0 ){ // 说明已经注册过了

                map = MapMessage.returnMapMessage(map,"error",
                        "手机号已经注册！","");
                return map;
            }


            if(count_wait_login == 0){ //不存在，则进行插入操作

                user.setIdentifyingCode(Integer.parseInt(checkCode));
                user.setPhoneNumber(phoneNumber);
                user.setCodeCreatTime(format.format(new Date()));
                user.setModifiedTime(format.format(new Date()));

                user.setCreateTime(format.format(new Date()));
                user.setState(0);//待注册状态
                try{
                    userService.add(user);
                }catch(Exception e){

                    map = MapMessage.returnMapMessage(map,"error",
                            "保存用户信息失败!",e.getMessage());
                    e.printStackTrace();
                    return map;
                }


            }else{ //存在，进行更新操作

                //*******************************
                paramMap.put("state", 0); //注册状态为0   进行更新操作
                try{
                    list = userService.getList(paramMap);
                }catch (Exception e){

                    map = MapMessage.returnMapMessage(map,"error",
                            "查询用户信息失败!",e.getMessage());
                    e.printStackTrace();
                    return map;
                }

                user = list.get(0) ;
                user.setIdentifyingCode(Integer.parseInt(checkCode));
                user.setPhoneNumber(phoneNumber);
                user.setCodeCreatTime(format.format(new Date()));
                user.setModifiedTime(format.format(new Date()));

                try{
                    userService.update(user);
                }catch(Exception e){

                    map = MapMessage.returnMapMessage(map,"error",
                            "保存用户信息失败!",e.getMessage());
                    e.printStackTrace();
                    return map;
                }

            }


            /******重置密码发送验证码********/
        }else if(sign == 1){  //重置密码发送验证码


            if(count_done_login == 0 ){ // 说明未注册

                map = MapMessage.returnMapMessage(map,"error",
                        "手机号未注册，请先注册！","");
                return map;
            }

            if(count_done_login !=0){

                paramMap.put("state", 1);
                try{
                    list = userService.getList(paramMap);
                }catch (Exception e){

                    map = MapMessage.returnMapMessage(map,"error",
                            "查询用户信息失败!",e.getMessage());
                    e.printStackTrace();
                    return map;
                }

                user = list.get(0);
                user.setIdentifyingCode(Integer.parseInt(checkCode));
                user.setPhoneNumber(phoneNumber);
                user.setCodeCreatTime(format.format(new Date()));
                user.setModifiedTime(format.format(new Date()));

                try{
                    userService.update(user);
                }catch(Exception e){

                    map = MapMessage.returnMapMessage(map,"error",
                            "保存用户信息失败!",e.getMessage());
                    e.printStackTrace();
                    return map;
                }
            }

        }


         //发短信
        SendSmsResponse response = sendSms(phoneNumber,checkCode);
        //sms2.send("18258204106",checkCode);
        if(response.getCode().equals("OK")){
            map = MapMessage.returnMapMessage(map,"success",
                    "发送短信验证码成功!","");
        }else {
            map = MapMessage.returnMapMessage(map,"error",
                    "发送短信验证码失败！","");
        }
        return map;
    }

}
