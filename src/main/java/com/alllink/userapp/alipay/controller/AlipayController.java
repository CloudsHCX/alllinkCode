package com.alllink.userapp.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alllink.commons.enums.OrderState;
import com.alllink.commons.enums.PaymentChannel;
import com.alllink.commons.pay.AlipayConfig;
import com.alllink.userapp.activity.entity.ActivityDetailItem;
import com.alllink.userapp.activity.entity.ActivityEntity;
import com.alllink.userapp.activity.service.ActivityService;
import com.alllink.userapp.order.dao.OrderDao;
import com.alllink.userapp.order.entity.OrderEntity;
import com.alllink.userapp.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static com.alllink.commons.pay.AlipayConfig.charset;

/**
 * 支付宝支付，退款
 */


@Controller
@RequestMapping(value = "/alipay")
public class AlipayController extends AlipayApiException {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ActivityService activityService;

    /**
     * 支付宝付款
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/payWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object pay(HttpServletRequest httpRequest,
                      HttpServletResponse httpResponse, @RequestBody Map<String, Object> paramMap) throws ServletException, IOException {
        Map<String, Object> map = new HashMap<String, Object>();

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }

        //获取订单具体信息
        List<OrderEntity> list = null;
        list = orderService.getList(paramMap);
        if (list == null) {
            map.put("result", "error");
            return map;
        }



        String orderIdStr = paramMap.get("orderIdStr").toString();
        String totalAmount = list.get(0).getCost().toString();
        String subjectG = list.get(0).getActivityName();



        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        //String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = orderIdStr;
        //付款金额，必填
        //String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        String total_amount = totalAmount;
        //订单名称，必填
        //String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        String subject = subjectG;
        //商品描述，可空
        //String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        String body = new String("");


        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"body\":\"" + body + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        String result = "";
        try {

            result = alipayClient.pageExecute(alipayRequest).getBody();

        } catch (AlipayApiException e) {
            e.printStackTrace();
            map.put("result", "error");
            return map;
            //return map;
        }

        map.put("data", result);
        map.put("result", "success");

        return map;
    }



    /**
     * 审核成功后：退款
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/refundWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object refundWeb(@RequestBody Map<String, Object> paramMap) {

        //目前认为退款金额和付款进金额一致
        Map<String, Object> map = new HashMap<String, Object>();


        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }
        if (paramMap.get("totalAmount") == null) {
            map.put("result", "error");
            return map;
        }


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        //String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_trade_no = paramMap.get("orderIdStr").toString();
        //支付宝交易号
        //String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        String trade_no = "";
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        //String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        String refund_amount = paramMap.get("totalAmount").toString(); //这里认为退款金额等于付款金额
        //退款的原因说明
        //String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        String refund_reason = ""; //不确定要不要前台进行传进来
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        //String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");
        String out_request_no = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"refund_reason\":\"" + refund_reason + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");

        AlipayTradeRefundResponse response = null;

        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            map.put("result", "error");
            return map;
        }

        if (response.isSuccess()) {
            //调用成功 ----更新一些数据

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderIdStr(out_trade_no);
            orderEntity.setOrderState(OrderState.OrderRefund.getValue());
            orderEntity.setPaymentChannel(PaymentChannel.alipay.getValue());
            try {
                orderService.modifyOrder(orderEntity);
            } catch (Exception e) {

                map.put("result", "error");
                return map;
            }


        } else {
            map.put("result", "error");
            return map;
        }


        map.put("result", "success");
        return map;
    }

    /**
     * web端的获取支付的异步结果: 目前不打算用这个，而是同步通知
     * @param httpRequest
     * @param httpResponse
     */
    @RequestMapping(value = "/notifyUrlWeb", method = RequestMethod.POST)
    public void notifyUrl(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        /* *
         * 功能：支付宝服务器异步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


         *************************页面功能说明*************************
         * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
         * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
         * 如果没有收到该页面返回的 success
         * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
         */

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = httpRequest.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            } catch (IOException e) {

            }

            params.put(name, valueStr);
        }

        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, charset, AlipayConfig.sign_type); //调用SDK验证签名

        } catch (AlipayApiException e) {
            e.printStackTrace();

        }


        //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */

        String out_trade_no = "";
        String trade_no = "";
        String trade_status = "";
        String total_amount = "";
        PrintWriter out = null;
        try {

            out = httpResponse.getWriter();

        } catch (IOException e) {
            e.getStackTrace();
        }

        if (signVerified) {//验证成功
            //商户订单号
            try {
                out_trade_no = new String(httpRequest.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //支付宝交易号
                trade_no = new String(httpRequest.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

                //交易状态
                trade_status = new String(httpRequest.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
                total_amount = new String(httpRequest.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");


            } catch (IOException e) {

            }


            if (trade_status.equals("TRADE_FINISHED")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            } else if (trade_status.equals("TRADE_SUCCESS")) {
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知

                /****在这里添加alipay信息*****/
                //这里面获得必要信息，插入数据库

                System.out.println("进入异步函数付款成功阶段");

            }
            System.out.println("输出success");



            out.println("success");


        } else {//验证失败
            out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——


    }


    /**
     * 查询付款
     * @param paramMap
     * @return
     */

    @RequestMapping(value = "/payQueryWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object payQuery(@RequestBody Map<String, Object> paramMap) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = paramMap.get("orderIdStr").toString();
        //支付宝交易号
        String trade_no = "";
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");

        //请求
        String result = "";
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.getStackTrace();
            map.put("result", "error");
            return map;
        }


        map.put("date", result);
        map.put("result", "success");
        return map;
    }

    /**
     *
     * 查询退款
     * @param paramMap
     * @return
     */
    @RequestMapping(value = "/refundQueryWeb", method = RequestMethod.POST)
    @ResponseBody
    public Object refundQuery(@RequestBody Map<String, Object> paramMap) {

        Map<String, Object> map = new HashMap<String, Object>();
        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }

        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = paramMap.get("orderIdStr").toString();
        //支付宝交易号
        String trade_no = "";
        //请二选一设置
        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
        String out_request_no = "";

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"trade_no\":\"" + trade_no + "\","
                + "\"out_request_no\":\"" + out_request_no + "\"}");

        //请求
        String result = "";
        try {
            result = alipayClient.execute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.getStackTrace();
            map.put("result", "error");
            return map;
        }

        map.put("date", result);
        map.put("result", "success");
        return map;
    }


    /**
     *  app端支付宝付款
     * @param paramMap
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/payApp", method = RequestMethod.POST)
    @ResponseBody
    public Object payApp(@RequestBody Map<String, Object> paramMap) throws ServletException, IOException {

        Map<String, Object> map = new HashMap<String, Object>();

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }

        //获取订单具体信息
//        List<OrderEntity> list =null;
//        list = orderService.getList(paramMap);
//        if(list ==null){
//            map.put("result","error");
//            return map;
//        }


        String orderIdStr = paramMap.get("orderIdStr").toString();
        String totalAmount = paramMap.get("totalAmount").toString();
        String subjectG = paramMap.get("subject").toString();


        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("我是测试数据");
        model.setSubject(subjectG);
        model.setOutTradeNo(orderIdStr);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(totalAmount);
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("商户外网可以访问的异步地址");

        String orderString = "";
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            orderString = response.getBody();//就是orderString 可以直接给客户端请求，无需再做处理。
        } catch (AlipayApiException e) {
            e.printStackTrace();
            map.put("result", "error");
            return map;
        }

        map.put("data", orderString);
        map.put("result", "success");

        return map;
    }


    @RequestMapping(value = "/notifyUrlApp", method = RequestMethod.POST)
    public void notifyUrlApp(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {


        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = httpRequest.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }


        //切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)

        boolean flag = false;

        try {
            flag = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, charset, "RSA2");

        } catch (AlipayApiException e) {
            e.getStackTrace();
        }


        //继续处理函数
        //能否用异步 要测试一下


    }


    @RequestMapping(value = "/refundApp", method = RequestMethod.POST)
    @ResponseBody
    public Object refundApp(@RequestBody Map<String, Object> paramMap) {


        //目前认为退款金额和付款进金额一致
        Map<String, Object> map = new HashMap<String, Object>();

        String orderIdStr = "";
        String totalAmount = "";

        if (paramMap.get("orderIdStr") == null) {
            map.put("result", "error");
            return map;
        }
        if (paramMap.get("totalAmount") == null) {
            map.put("result", "error");
            return map;
        }

        orderIdStr = paramMap.get("orderIdStr").toString();
        totalAmount = paramMap.get("totalAmount").toString();


        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
        request.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"trade_no\":\"2014112611001004680073956707\"," +
                "    \"out_request_no\":\"1000001\"," +
                "    \"refund_amount\":\"1\"" +
                "  }");//设置业务参数


        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);//通过alipayClient调用API，获得对应的response类
            //System.out.print(response.getBody());

        } catch (AlipayApiException e) {
            e.getStackTrace();
            map.put("result", "error");
            return map;
        }

        // 根据response中的结果继续业务逻辑处理
        if (response.isSuccess()) {
            //处理订单状态
            //*******************业务层

            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setOrderIdStr(orderIdStr);
            orderEntity.setOrderState(OrderState.OrderRefund.getValue());
            orderEntity.setPaymentChannel(PaymentChannel.alipay.getValue());
            try {
                orderService.modifyOrder(orderEntity);
            } catch (Exception e) {
                //这个不能认为是退款异常，
                map.put("result", "error");
                return map;
            }
        } else {

            map.put("result", "error");
            return map;
        }


        map.put("result", "success");
        return map;
    }


}
