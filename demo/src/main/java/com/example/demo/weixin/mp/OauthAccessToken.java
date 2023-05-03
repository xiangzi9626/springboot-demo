package com.example.demo.weixin.mp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.example.demo.util.HttpUtil;
import com.example.demo.weixin.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 获取网页授权access_token与基础access_token不同
 */
public class OauthAccessToken {
   private static final String APPID = Config.MpAPPID;
    private static final String APPSECRET = Config.MpAPPSECRET;
    private static final String filePath = "D:/weixin/oauthAccessToken.txt";

    /**
     * 获取网页授权access_token
     * @param code 通过code换取access_token
     * @return
     */
    public static JsonObject getAccessToken(String code) {
        JsonObject jb = new JsonObject();
        FileReader reader;
        Gson gson = new Gson();
        if (!FileUtil.exist(filePath)) {
            return writeTxt(code);
        }
        reader = new FileReader(filePath);
        String accessToken = reader.readString();
        if (accessToken.equals("")) {
            //System.out.print("文件内容为空写入内容\n");
            return writeTxt(code);
        }
        jb = gson.fromJson(accessToken, JsonObject.class);
        Long expireIn = Long.parseLong(jb.get("expires_in") + "");
        if (jb.get("access_token") == null){
            return writeTxt(code);
        }
        String token=jb.get("access_token").toString();
        String openid_str=jb.get("openid").toString();
        String refresh_token_str=jb.get("refresh_token").toString();
        String access_token=token.substring(1,token.length()-1);
        String openid=openid_str.substring(1,openid_str.length()-1);
        String refresh_token=refresh_token_str.substring(1,refresh_token_str.length()-1);
        if (System.currentTimeMillis() / 1000 > expireIn || !checkAccessToken(access_token,openid)) {
            //System.out.print("refresh_token");
             return refreshToken(refresh_token);
        }
         return jb;
    }

    /**
     * 检测access_token是否有效
     * @return
     */
    public static boolean checkAccessToken(String access_token,String openId){
        String checkUrl="https://api.weixin.qq.com/sns/auth?access_token="+access_token+"&openid="+openId;
        String resp=HttpUtil.get(checkUrl);
         Gson gson=new Gson();
         JsonObject jb=gson.fromJson(resp,JsonObject.class);
          if (jb.get("errmsg").toString().equals("\"ok\"")){
             return true;
         }
        return false;
    }
    public static JsonObject writeTxt(String code) {
        String getAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+APPSECRET+"&code="+code+"&grant_type=authorization_code";
        String accessToken = "";
        Gson gson = new Gson();
        JsonObject jb = new JsonObject();
        FileWriter writer;
         accessToken = HttpUtil.get(getAccessTokenUrl);
        jb = gson.fromJson(accessToken, JsonObject.class);
        jb.addProperty("expires_in", System.currentTimeMillis() / 1000 + 7150);
        writer = new FileWriter(filePath);
        writer.write(jb.toString());
        //String str = jb.get("access_token") + "";
        return jb;
    }
    /**
     * 获取用户信息
     */
    public static String getWxUserInfo(String code){
        String token_str=getAccessToken(code).get("access_token").toString();
        String access_token=token_str.substring(1,token_str.length()-1);
        String openid_str=getAccessToken(code).get("openid").toString();
        String openid=openid_str.substring(1,openid_str.length()-1);
        String url="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
        String resp=HttpUtil.get(url);
       return resp;
    }
    public static JsonObject refreshToken(String refresh_token){
        String getAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+refresh_token;
        String accessToken = "";
        Gson gson = new Gson();
        JsonObject jb = new JsonObject();
        FileWriter writer;
        accessToken = HttpUtil.get(getAccessTokenUrl);
        jb = gson.fromJson(accessToken, JsonObject.class);
        jb.addProperty("expires_in", System.currentTimeMillis() / 1000 + 7150);
        writer = new FileWriter(filePath);
        writer.write(jb.toString());
        //String str = jb.get("access_token") + "";
        return jb;
    }
}
