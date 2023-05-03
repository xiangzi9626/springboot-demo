package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;

public class Menu {
    public static void setMenu() {
        String menu = "{";
        menu += "\"button\":[";
        menu += "{\"type\":\"click\",\"name\":\"今日头条\",\"key\":\"BTN001\"},";
        menu += "{\"name\":\"菜单\",";
        menu += "\"sub_button\":";
        menu += "[";
        menu += "{";
        menu += "\"type\":\"view\",";
        menu += "\"name\":\"网页授权\",";
        menu += "\"url\":\"http://170515075.51vip.biz/wxoauth\"";
        menu += "}";
        menu += "]";
        menu += "}";
        menu += "]";
        menu += "}";
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken;
        String resp = HttpUtil.post(url, menu);
        //System.out.print(resp);
        //System.out.print(menu);
    }
}
