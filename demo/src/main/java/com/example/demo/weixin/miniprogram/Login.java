package com.example.demo.weixin.miniprogram;

import com.example.demo.util.HttpUtil;
import com.example.demo.weixin.Config;

public class Login {
    /**
     * 属性	类型	默认值	必填	说明
     * appid	string		是	小程序 appId
     * secret	string		是	小程序 appSecret
     * js_code	string		是	登录时获取的 code
     * grant_type	string		是	授权类型，此处只需填写 authorization_code
     */
    public static void wxLogin(String JSCODE){
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+ Config.MiniprogramAPPID +"&secret="+Config.MiniprogramAPPSECRET+"&js_code="+JSCODE+"&grant_type=authorization_code";
        String resp= HttpUtil.get(url);
    }
}
