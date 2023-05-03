package com.example.demo.weixin.mp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import com.example.demo.util.HttpUtil;
import com.example.demo.weixin.Config;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class AccessToken {
    private static final String getAccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String grant_type = "client_credential";
    private static final String APPID = Config.MpAPPID;
    private static final String APPSECRET = Config.MpAPPSECRET;
    private static final String filePath = "D:/weixin/accessToken.txt";

    /**
     * 获取AccessToken
     *
     * @return
     */
    public static String getAccessToken() {
        JsonObject jb = new JsonObject();
        FileReader reader;
        Gson gson = new Gson();
        if (!FileUtil.exist(filePath)) {
            return writeTxt();
        }
        reader = new FileReader(filePath);
        String accessToken = reader.readString();
        if (accessToken.equals("")) {
            System.out.print("文件内容为空写入内容\n");
            return writeTxt();
        }
        jb = gson.fromJson(accessToken, JsonObject.class);
        Long expireIn = Long.parseLong(jb.get("expires_in") + "");
        if (System.currentTimeMillis() / 1000 > expireIn || jb.get("access_token") == null) {
            System.out.print("刷新accessToken");
            return writeTxt();
        }
        String str = jb.get("access_token").toString();
        return str.substring(1, str.length() - 1);
    }

    public static String writeTxt() {
        String accessToken = "";
        Gson gson = new Gson();
        JsonObject jb = new JsonObject();
        FileWriter writer;
        String url = getAccessTokenUrl + "?grant_type=" + grant_type + "&appid=" + APPID + "&secret=" + APPSECRET;
        accessToken = HttpUtil.get(url);
        jb = gson.fromJson(accessToken, JsonObject.class);
        jb.addProperty("expires_in", System.currentTimeMillis() / 1000 + 7150);
        writer = new FileWriter(filePath);
        writer.write(jb.toString());
        String str = jb.get("access_token") + "";
        return str.substring(1, str.length() - 1);
    }
}
