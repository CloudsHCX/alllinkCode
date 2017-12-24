package com.alllink.sellerapp.seller.controller;

import com.alllink.commons.utils.R;
import com.alllink.commons.utils.RandomNumberUtil;
import com.alllink.sellerapp.seller.entity.SellerEntity;
import com.alllink.sellerapp.seller.service.SellerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileUpload {

    @Autowired
    private SellerService sellerService;

    //头像上传
    @RequestMapping(value = "/image", method = RequestMethod.POST)
    @ResponseBody
    public R imageUpload(MultipartFile file, String sellerId, HttpServletRequest request) throws IOException {
       //String contextPath = request.getSession().getServletContext().getRealPath("/");//("/WEB-INF/update");
        String path = "D:\\image\\";

        //获取上传文件名称
        String fileName = file.getOriginalFilename();
        System.out.println("fileName:" + fileName);

        //获取文件名hashCode
        int hashCode = fileName.hashCode();
        //获取hashCode的低4位，并转换成16进制字符串
        String dir1 = Integer.toHexString(hashCode & 0xF);
        //获取hashCode的低5~8位，并转换成16进制字符串
        String dir2 = Integer.toHexString(hashCode >>> 4 & 0xF);
        //与文件保存目录连接成完整路径
        path = path + "/" + dir1 + "/" + dir2;
        //文件名重命名，UUID_fileName方式
        fileName = RandomNumberUtil.CreateUUID() + "_" + fileName;
        System.out.println("path:" + path + "fileName: " +fileName);

        File dir = new File(path, fileName);
        System.out.println("dir.exists():" + dir);

        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();//创建文件夹
        }
        file.transferTo(dir);

//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("url",dir.toString());
//        return jsonObj;
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(dir.toString().substring(9));
        map.put("url","\\pic\\" + dir.toString().substring(9));
        SellerEntity sellerEntity = new SellerEntity();
        sellerEntity.setPhoto((String) map.get("url"));
        sellerEntity.setSellerId(Integer.parseInt(sellerId));
        sellerService.update(sellerEntity);
        Map<String, Object> sellerMap = new HashMap<>();
        sellerMap.put("seller", sellerService.findSellerById(Integer.parseInt(sellerId)));
        HttpSession session = request.getSession();
        session.setAttribute("seller", sellerMap);

        return R.ok(map);
    }

    //图片上传
    @RequestMapping(value = "/photo", method = RequestMethod.POST)
    @ResponseBody
    public R photoUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        //String contextPath = request.getSession().getServletContext().getRealPath("/");//("/WEB-INF/update");
        String path = "D:\\image\\";
        System.out.println("path:" + path);

        //获取上传文件名称
        String fileName = file.getOriginalFilename();
        System.out.println("fileName:" + fileName);

        //获取文件名hashCode
        int hashCode = fileName.hashCode();
        //获取hashCode的低4位，并转换成16进制字符串
        String dir1 = Integer.toHexString(hashCode & 0xF);
        //获取hashCode的低5~8位，并转换成16进制字符串
        String dir2 = Integer.toHexString(hashCode >>> 4 & 0xF);
        //与文件保存目录连接成完整路径
        path = path + "/" + dir1 + "/" + dir2;
        //文件名重命名，UUID_fileName方式
        fileName = RandomNumberUtil.CreateUUID() + "_" + fileName;
        System.out.println("path:" + path + "fileName: " +fileName);

        File dir = new File(path, fileName);
        System.out.println("dir.exists():" + dir);

        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();//创建文件夹
        }
        file.transferTo(dir);

//        JSONObject jsonObj = new JSONObject();
//        jsonObj.put("url",dir.toString());
//        return jsonObj;
        HashMap<String, Object> map = new HashMap<>();
        System.out.println(dir.toString().substring(9));
        map.put("url","\\pic\\" + dir.toString().substring(9));

        return R.ok(map);
    }


    //多文件上传
    @RequestMapping(value = "/filesUpload", method = RequestMethod.POST)
    @ResponseBody
    public List<String> FilesUpload(MultipartFile files[],HashMap<String, Object> map) throws IOException {

        List<String> newFileNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            //文件的原始名称
            String originalFilename = multipartFile.getOriginalFilename();
            String newFileName = null;
            if (multipartFile != null && originalFilename != null && originalFilename.length() > 0) {
                newFileName = RandomNumberUtil.CreateUUID() + "_" + originalFilename;
                //存储图片的物理路径
                String path = "D:\\image\\";
                //获取文件名hashCode
                int hashCode = originalFilename.hashCode();
                //获取hashCode的低4位，并转换成16进制字符串
                String dir1 = Integer.toHexString(hashCode & 0xF);
                //获取hashCode的低5~8位，并转换成16进制字符串
                String dir2 = Integer.toHexString(hashCode >>> 4 & 0xF);
                //与文件保存目录连接成完整路径
                path = path + "/" + dir1 + "/" + dir2;
                File dir = new File(path, newFileName);
                if (!dir.exists()) {// 判断目录是否存在
                    dir.mkdirs();//创建文件夹
                }
                multipartFile.transferTo(dir);
                newFileNames.add("\\pic\\" + dir.toString().substring(9));
            }
        }
        return newFileNames;
    }

}
