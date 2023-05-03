package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 账号管理
 */
public class Account {
    /**
     * 创建临时二维码(数字)
     *
     * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @param scene_id 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return
     */
    public static String createTempTicket(int expire_seconds, int scene_id) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        String param = "{\"expire_seconds\":" + expire_seconds + ", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":" + scene_id + "}}}";
        String resp = HttpUtil.post(url, param);
         Gson gson=new Gson();
         JsonObject wxOrcode=gson.fromJson(resp,JsonObject.class);
        return wxOrcode.get("url").toString();
    }
    /**
     * 创建临时二维码(字符串)
     *
     * @param expire_seconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return
     */
    public static String createTempTicket(int expire_seconds, String sceneStr) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        String param = "{\"expire_seconds\":" + expire_seconds + ", \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":" + sceneStr + "}}}";
        String resp = HttpUtil.post(url, param);
        Gson gson=new Gson();
        JsonObject wxOrcode=gson.fromJson(resp,JsonObject.class);
        return wxOrcode.get("url").toString();
    }
    /**
     * 创建永久二维码(数字)
     *
     * @param scene_id 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return
     */
    public static String createForeverTicket(int scene_id) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        String param = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":" + scene_id + "}}}";
        String resp = HttpUtil.post(url, param);
        Gson gson=new Gson();
        JsonObject wxOrcode=gson.fromJson(resp,JsonObject.class);
        return wxOrcode.get("url").toString();
    }
    /**
     * 创建永久二维码(字符串)
     *
     * @param scene_str 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
     * @return
     */
    public static String createForeverTicket(String scene_str) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + accessToken;
        String param = "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\":" + scene_str + "}}}";
        String resp = HttpUtil.post(url, param);
        Gson gson=new Gson();
        JsonObject wxOrcode=gson.fromJson(resp,JsonObject.class);
        return wxOrcode.get("url").toString();
    }
    /**
     * 获取二维码ticket后，通过ticket换取二维码图片展示
     *
     * @param ticket
     * @return
     */
    public static String showQrCode(String ticket) throws UnsupportedEncodingException {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+URLEncoder.encode(ticket,"utf-8");
        return "";
    }
}
