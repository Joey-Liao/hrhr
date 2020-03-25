package com.uestc.appinterface.ToolUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONObject;

/**
 * @author:WangZiBin
 * @description:http发送工具类
 */
public class HttpHelper {
    static Logger log = LoggerFactory.getLogger(HttpHeaders.class);
    /**
     * 向目的URL发送post请求
     * @param url       目的url
     * @param json    发送的参数
     * @return  AdToutiaoJsonTokenData
     */
    public static JSONObject sendPostRequest(String url, JSONObject json,HttpHeaders headers){

        log.info(url);
        RestTemplate client = new RestTemplate();
        //新建Http头，add方法可以添加参数
        //设置请求发送方式
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        //将请求头部和参数合成一个请求
        HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
        //执行HTTP请求，将返回的结构使用String 类格式化（可设置为对应返回值格式的类）
        JSONObject result = client.postForObject(url, entity, JSONObject.class);
        if(!result.getString("code").equals("0"))
        {
            //失败再请求一次
            //result = client.postForObject(url, entity, JSONObject.class);
        }
        return result;
    }

    /**
     * 向目的URL发送get请求
     * @param url       目的url
     * @param headers   发送的http头，可在外部设置好参数后传入
     * @return  String
     */
    public static JSONObject sendGetRequest(String url,HttpHeaders headers){
        RestTemplate client = new RestTemplate();

        HttpMethod method = HttpMethod.GET;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_JSON);
        //将请求头部和参数合成一个请求
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        ResponseEntity<JSONObject> result = client.exchange(url, method, entity, JSONObject.class);
        if(!result.getBody().getString("code").equals("0"))
        {
            //失败再请求一次
            //result = client.exchange(url, method, entity, JSONObject.class);
        }
        return  result.getBody();
    }
}


