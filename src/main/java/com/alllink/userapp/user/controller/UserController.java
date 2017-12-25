package com.alllink.userapp.user.controller;


import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.alllink.commons.exception.MapMessage;
import com.alllink.commons.utils.CheckCodeUtil;
import com.alllink.commons.utils.CheckDevice;
import com.alllink.commons.utils.JsonUtils;
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
import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.alllink.commons.sms.smsdemo.sendSms;


@Controller
@RequestMapping(value = "/user")
public class UserController {
    public static Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;




    @RequestMapping(value="/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public Object ResetPassword(HttpServletRequest request,@RequestBody Map<String, Object> paramMap){

        Map<String, Object> map=new HashMap<String, Object>();

        List<User> list  = null;
        User user = null ;
        String password = null;
        String phoneNumber = null;
        String checkcode = null;
        int count = 0;

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


        paramMap.remove("password");
        paramMap.remove("checkcode");
        //查询---用户是否存在
        paramMap.put("state",1);
        try{
            count = userService.getListCount(paramMap);

        }catch(Exception e){

            map = MapMessage.returnMapMessage(map,"error",
                    "查询用户失败！",e.getMessage());
            e.printStackTrace();
            return map;
        }

        if(count == 0){

            map = MapMessage.returnMapMessage(map,"error",
                    "手机号不存在，请先注册！","");
        }

        if(count != 0){
            try{
                 list = userService.getList(paramMap);
            }catch(Exception e){
                map = MapMessage.returnMapMessage(map,"error",
                        "查询用户失败！",e.getMessage());
                e.printStackTrace();
                return map;

            }
            //判断验证码是否过期
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


            //加密密码
//            String  encryptPassword = SHAUtil.SHAEncode(password);
//            user.setPassword(encryptPassword);
            user.setPassword(password);
            user.setModifiedTime(format.format(new Date()));

            /***更新操作***/

            try{
                userService.update(user);

            }catch (Exception e){
                map = MapMessage.returnMapMessage(map,"error",
                        "重置密码失败！",e.getMessage());
                e.printStackTrace();
                return map;

            }
        }


        map = MapMessage.returnMapMessage(map,"success",
                "重置密码成功,即将跳转登录页面！","");
        return map;
    }

    /**
     * 获取用户基本信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value={"/getUserBasicInfo","/getUserThirdPartyAccount"},method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String upCreate(@RequestBody User user,HttpServletRequest request){
        User u = null;
        if(CheckDevice.getDevice(request)==1){
            u = userService.getUserInfo(user.getPhoneNumber());
            String photo = u.getPhoto();
            u.setPhoto("http://101.132.191.9:8083/pic/"+photo);
        } else {
            u = userService.getUserInfoInWeb(Integer.parseInt(request.getSession().getAttribute("user").toString()));
        }
        Map<String,Object>map = new HashMap<String, Object>();
        if (u!=null) {
            map.put("data", u);
            map.put("result", "success");
            map.put("message", "获取用户信息成功");
        }else{
            map.put("data", u);
            map.put("result", "error");
            map.put("message", "获取用户信息失败");
        }
        return JsonUtils.objectToJson(map);
    }

    /**
     * 更新用户基本信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    public String updateUserInfo(@RequestBody User user) {
        Map<String,String> map = new HashMap<>();
        //设置修改时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String da = sdf.format(date);
        user.setModifiedTime(da);
        //提交表单
        try {
            userService.updateUserinfo(user);
            map.put("result","success");
            map.put("exception","");
            map.put("message","修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result","修改失败");
            map.put("exception","");
            map.put("message","修改失败");
        }
        return JsonUtils.objectToJson(map);


    }

    /**
     * 修改用户头像
     * @param avatarFile
     * @param phoneNumber
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPic",method = RequestMethod.POST)
    public String filePhoto(MultipartFile avatarFile, String phoneNumber, HttpServletRequest request) throws Exception{
        String filStr = avatarFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()+filStr.substring(filStr.lastIndexOf("."));
        User user = new User();
        user.setPhoneNumber(phoneNumber);
        User u = userService.getUserInfo(user.getPhoneNumber());

        File preF = new File("D:\\image\\"+u.getPhoto());

        if (preF!=null){
            preF.delete();

        }
        avatarFile.transferTo(new File("D:\\image\\"+newName));
        user.setPhoto(newName);
        userService.updateUserinfo(user);
        if (CheckDevice.getDevice(request)==1){
            Map<String,String> map = new HashMap<>();
            map.put("imageUrl","http://101.132.191.9:8083/pic/"+newName);
            map.put("result","success");
            map.put("exception","");
            map.put("message","头像上传成功");
            return JsonUtils.objectToJson(map);
        }else {
            return "/pic/" + newName;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfoFromSession", method = RequestMethod.POST)
    public Object getUserInfoFromSession(HttpServletRequest request) {

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        HttpSession httpSession = request.getSession();

        String userId = null;
        if (httpSession.getAttribute("user") == null) {
            map.put("message", "session已过期，请重新登录！");
            map.put("result", "error");
            return map;
        }
        userId = httpSession.getAttribute("user").toString();
        paramMap.put("userId", userId);
        User user = new User();
        List<User> list = null;
        try {
            list = userService.getList(paramMap);
        } catch (Exception e) {
            map.put("result", "error");
            return map;
        }

        if (list == null) {
            map.put("result", "error");
            return map;
        }
        String photoUrl = list.get(0).getPhoto();
        String phoneNumber = list.get(0).getPhoneNumber();

        map.put("photoUrl", photoUrl);
        map.put("phoneNumber", phoneNumber);
        map.put("result", "success");

        return map;
    }
}
