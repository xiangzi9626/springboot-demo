package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;
import com.google.gson.JsonObject;

public class TemplateMsg {
    /**
     * 设置行业
     */
    public static String setIndustry(){
        String accessToken=AccessToken.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+accessToken;
        String resp=HttpUtil.post(url,"{\"industry_id1\":\"1\",\"industry_id2\":\"4\"}");
        return resp;
    }

    /**
     * 获取行业
     */
    public static String getIndustry(){
        String accessToken=AccessToken.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token="+accessToken;
        String resp=HttpUtil.get(url);
        return resp;
    }

    /**
     * 获取模板消息列表
     * @return
     */
    public static String getTemplateList(){
        String accessToken=AccessToken.getAccessToken();
        String url="https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="+accessToken;
        String resp=HttpUtil.get(url);
        return resp;
    }
    /**
     * 发磅模板消息
     * 参数	是否必填	说明
     * touser	是	接收者openid
     * template_id	是	模板ID
     * url	否	模板跳转链接（海外帐号没有跳转能力）
     * miniprogram	否	跳小程序所需数据，不需跳小程序可不用传该数据
     * appid	是	所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * pagepath	否	所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），要求该小程序已发布，暂不支持小游戏
     * data	是	模板数据
     * color	否	模板内容字体颜色，不填默认为黑色
     */
    public static String sendTemplateMsg(String OPENID,String template_id,String url,String miniprogramAppId,String miniprogramPagePath,String value,String color,String value1,String color1,String value2,String color2,String value3,String color3,String value4,String color4){
        String accessToken=AccessToken.getAccessToken();
        String httpUrl=" https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        String param=" {";
                param+="\"touser\":"+"\""+OPENID+"\""+",";
        param+="\"template_id\":"+"\""+template_id+"\""+",";
        param+="\"url\":"+"\""+url+"\""+",";
        if (!miniprogramAppId.equals("")){
        param+="\"miniprogram\":{";
        param+="\"appid\":"+"\""+miniprogramAppId+"\""+",";
        param+="\"pagepath\":"+"\""+miniprogramPagePath+"\""+"},";
        }
        param+="\"data\":{";
        param+="\"first\":{";
        param+="\"value\":"+"\""+value+"\""+",";
        param+="\"color\":"+"\""+color+"\""+"},";
        param+="\"keyword1\":{";
        param+="\"value\":"+"\""+value1+"\""+",";
        param+="\"color\":"+"\""+color1+"\""+"},";
        param+="\"keyword2\":{";
        param+="\"value\":"+"\""+value2+"\""+",";
        param+="\"color\":"+"\""+color2+"\""+"},";
        param+="\"keyword3\": {";
        param+="\"value\":"+"\""+value3+"\""+",";
        param+="\"color\":"+"\""+color3+"\""+"},";
        param+="\"remark\":{";
        param+="\"value\":"+"\""+value4+"\""+",";
        param+="\"color\":"+"\""+color4+"\""+"}";
        param+="}";
        param+="}";
        //System.out.print(param);
        String resp=HttpUtil.post(httpUrl,param);
         return resp;
    }
}
