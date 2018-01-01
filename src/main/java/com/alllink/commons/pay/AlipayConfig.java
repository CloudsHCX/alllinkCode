package com.alllink.commons.pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016082700320775";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC/9ANza18f4C8Jkw/keoTlk3cMMYfNFATpLoyYMygOiZy4lT/2n7w/5qJ+vt1yFwCps+e2rEHqZARrWIZCjNOBws5KwpqSVvQ6Pp4dJpw/825yRIuK1N+Uy99f97liVE68iBIlqkUt9avkVuTxho2ZQQw/qKktI7D2Jb51vyEOySKEBENShD0xn6ClwLXNqNSSQfgc9dwL1vWH7WTg/P/GLLblR1vwyvWBJc2QxhIeP9lP2v5N29iq598cCItYAtWltMe9V7arHqxOBI98iUeEY5NlTn+dHXcputmHLwEbR/3W+Hjy69u+k6TPDark5OPq/K9VEUjkoLFYliAA5L/1AgMBAAECggEAfOy2Ajvy4K/qbPTE9xG1xCPA6rKhixpoR8n/vl4sx6lP0eFwi3VR8tSLFKnrPQEzcT6gowZgr8cACUzHtDAqxtydnZIhPvRQZkeyZ45lSkmHq95oYqZADHrSmYaDpDbyN0jnZpP2Cf9FK4pR3c6QARmQey2tTK9Z0BioZ8FIJnInqYXTXN74Y2xXS4mO2HO5DjdBB6SFFb2L7idCkQGFeEXvt7lnftMOdeCSC6mv8uY4c5dqcfCceHh3O8K5dAb/DZ7Qu07Jk4FNC2g+xdUUgL6Yj/7IXL3K4PAC+o9qnb038udnqzxn47NR71oy7qcgUrhmHPQYGb6MSDHsanh1UQKBgQDn/8DB4AcUjZIFnB/hszuF8BsWhphKf8BbgiPXA1Ni/z0xriv2lyTbmOmIflmXQNe0bNh2lLGx1iPhR8GZRWsDQEwFr3GTp8fJi9eswkxX8H4zF9ey6pqwA5E24ICt3FAncOvvT3k1mQYL0vGOA88XCbJUb1iqziaWuDFCFTxXtwKBgQDTz7BORPqwurBE67V2GU6juySCR/5GggNWResQu7bfxfqWlldNGbk6Yn3DyT+J8EOnyvp5pXWkpuFU1XEYyAtzfODJAQJpxo6nVXVOMzFHH/cutcKE/M2vmscCjHMW+nMV8p8FQ97cBURKY8bSYGN5IM/cJlBqVsd4bzAX03ntswKBgFx3slNTwF6vwnib7pUdw+axeYpz4PUoj/mpGDHVsIGvq6tGeV1Shq1D+Cif+4Nuo8rU6z3wLBbue+SJnh9B7NIh6WsGr2UjpVyucSr5UkZmo1Yn9Z/pI/hto45U0tT5D7V4q4D5kx0Kvb8ut3pcCEltYveGXbfObND73x4wqplnAoGAd8t9QadNyeqsiy5s4TCLA5tymKSzMZmS3ZaJzlr8oS9N/SFmmtqzokvWKYeHAjL7cMkiRO7JzKf9xYc6gfOSVm0IjWaewMhmIkn5IJNfiYSQvsBaFfENPAZkDBL3fMWFlaI1QjsSronkB9bKum19lSJzrWWWEa6m/nyUTMV0rnUCgYAJAVJimp2SPtuwUSEY7w7SMfbnVNWcdKvXUvzPkY3dnOaNuMtBk+tUEF4/HFAmLF1OlcDEGAyXBcZV7N/l2FmbsrTdufMmiejg1JgSZFat6A2DjYVu/TLPXFYR1QT/TJUUyS/FSwEZJ5/DvV39c24zblTi9OFJusHuOfQCrWCYcg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxQOW3fFF6Wpz3YXDXKr1usGdOfIUqinY0ZenoHGugg/g1nMgSI9etjNrBB6vsOk0o8UtwzpbF3VEnAa3AWBQupWwpB//Oi+Qbc0MyvD8h+4Ic58FO5B/QxWnsKX+S6yT6R37CjeWLyvWgk+uBOZIH10jhM1Ozl9mLM9ztFk86oPHheIHyMpNha4BilWmspBNsWifIUgfsBdtIJxU2nXd7k1+oW01YaJbWveDThCfvUmH7w2hYB//aCOrwxLSYzhNIn9wmcyEk4lOOXEpjjFqZdHfBESZfPLNta+TYAZ9r7cxXq3wezVo2Znvyk3k4bxaDQ8XH2VSf5JIbJIOhRxIjwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "test";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://101.132.191.9:8083/alllink/userapp/testReturn";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

