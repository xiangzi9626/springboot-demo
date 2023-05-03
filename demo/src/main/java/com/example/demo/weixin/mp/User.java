package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;

public class User {
    /**
     * 获取已关注用户
     * @param NEXT_OPENID 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    public static String getUserList(String NEXT_OPENID){
        String accessToken=AccessToken.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken+"&next_openid="+NEXT_OPENID;
        String resp= HttpUtil.get(url);
        return resp;
    }

    /**
     * 通过openid获取用户信息
     * @param OPENID 用户的openid
     * @return
     */
    public static String getUserOne(String OPENID){
        String accessToken=AccessToken.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+OPENID+"&lang=zh_CN";
        return HttpUtil.get(url);
    }
}
