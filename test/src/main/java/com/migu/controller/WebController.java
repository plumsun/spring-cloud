package com.migu.controller;


import com.alibaba.fastjson.JSONObject;
import com.migu.gvpcore.util.LogUtil;
import com.migu.remote.SSORemote;
import com.migu.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZC
 * @date 2021/8/17 14:45
 */
@RestController
@RequestMapping("/user")
public class WebController {

    @Autowired
    private SSORemote ssoRemote;


    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public Response test(@RequestParam("id")String id) {

        JSONObject jsonObject = null;
        if(id.equals("1")){
            LogUtil.info("........." + id);
            String service = "http://localhost:8888";
            String ticket = "ST-4-7ClRTG21RfPeBwLLgbbm-o0dhXwDESKTOP-6K8RPUV";
            String format = "JSON";

            String response = ssoRemote.serviceValidate(service, ticket, format);
            LogUtil.info("response=" + response);


            jsonObject = JSONObject.parseObject(response);
            LogUtil.info("jsonObject=" + jsonObject);
        }
        if(id.equals("2")){
            LogUtil.info("........." + id);

            String service = "http://localhost:8888";
            String ticket = "ST-4-7ClRTG21RfPeBwLLgbbm-o0dhXwDESKTOP-6K8RPUV";
            String format = "JSON";

            String response = ssoRemote.serviceValidateV1(service, ticket, format);
            LogUtil.info("response=" + response);


            jsonObject = JSONObject.parseObject(response);
            LogUtil.info("jsonObject=" + jsonObject);
        }
        if(id.equals("3")){
            LogUtil.info("........." + id);

            String service = "http://localhost:8888";
            String ticket = "ST-4-7ClRTG21RfPeBwLLgbbm-o0dhXwDESKTOP-6K8RPUV";
            String format = "JSON";

            String response = ssoRemote.serviceValidateV2(service, ticket, format);
            LogUtil.info("response=" + response);


            jsonObject = JSONObject.parseObject(response);
            LogUtil.info("jsonObject=" + jsonObject);
        }

        if(id.equals("4")){
            LogUtil.info("........." + id);

            String service = "http://localhost:8888";
            String ticket = "ST-4-7ClRTG21RfPeBwLLgbbm-o0dhXwDESKTOP-6K8RPUV";
            String format = "JSON";

            String response = ssoRemote.serviceValidateV3(service, ticket, format);
            LogUtil.info("response=" + response);


            jsonObject = JSONObject.parseObject(response);
            LogUtil.info("jsonObject=" + jsonObject);
        }

        return Response.ok(jsonObject);
    }
}
