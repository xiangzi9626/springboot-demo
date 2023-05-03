package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;
import com.google.gson.JsonObject;

public class Material {
    public static String delMaterial(String MEDIA_ID) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=" + accessToken;
        JsonObject param=new JsonObject();
        param.addProperty("media_id",MEDIA_ID);
        String res = HttpUtil.post(url,param+"");
        return res;
    }

    /**
     * 获取素材列表
     *
     * @param type   素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return
     */
    public static String getMaterialList(String type, String offset, String count) {
        String accessToken = AccessToken.getAccessToken();
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + accessToken;
        JsonObject param = new JsonObject();
        param.addProperty("type", type);
        param.addProperty("offset", offset);
        param.addProperty("count", count);
        String res = HttpUtil.post(url, param + "");
        return res;
    }
}
