package com.xq.tmall.api;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取token类
 */
public class AuthService {

    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Secret Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String clientId = "a2NzXAYpPd7IU7W7SnYOlHrU";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "FUAUgo1yxxhueKzNow34MheL1mdn3KEP";
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("grant_type","client_credentials");
        paramMap.put("client_id",clientId);
        paramMap.put("client_secret",clientSecret);
        String res=HttpUtil.post(authHost,paramMap);
        Gson gson=new Gson();
      Map<String,String>  map=gson.fromJson(res,new HashMap<String,String>().getClass());
      return map.get("access_token");
    }
}
