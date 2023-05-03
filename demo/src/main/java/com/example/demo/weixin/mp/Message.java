package com.example.demo.weixin.mp;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * xml转为Map集合
 *
 * @author Administrator
 */
public class Message {
    public static Map<String, String> xmlToMap(InputStream is) {
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            //读取输入流,获取文档对象
            Document doc = reader.read(is);
            //根据文档对象获取根节点
            Element root = doc.getRootElement();
            //获取根节点所有的子节点
            //@SuppressWarnings("unchecked")
            List<Element> list = root.elements();
            for (Element e : list) {
                map.put(e.getName(), e.getStringValue());
            }
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String text(Map<String, String> map, String content) {
        String xml = "";
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[text]]></MsgType>";
        xml += "<Content><![CDATA[" + content + "]]></Content>";
        xml += "</xml>";
        return xml;
    }

    public static String image(Map<String, String> map, String mediaId) {
        String xml = "";
        //media_id 通过素材管理中的接口上传多媒体文件，得到的id
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[image]]></MsgType>";
        xml += "<Image><MediaId><![CDATA[" + mediaId + "]]></MediaId></Image>";
        xml += "</xml>";
        return xml;
    }

    public static String voice(Map<String, String> map, String mediaId) {
        String xml = "";
        //media_id 通过素材管理中的接口上传多媒体文件，得到的id
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[voice]]></MsgType>";
        xml += "<Voice><MediaId><![CDATA[" + mediaId + "]]></MediaId></Voice>";
        xml += "</xml>";
        return xml;
    }

    /**
     * 视频消息
     *
     * @param map
     * @param mediaId     素材接口的媒体media_id
     * @param title       可选 视频标题
     * @param description 可选 视频描述
     * @return
     */
    public static String video(Map<String, String> map, String media_id, String title, String description) {
        String xml = "";
        //media_id 通过素材管理中的接口上传多媒体文件，得到的id
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[video]]></MsgType>";
        xml += "<Video>";
        xml += "<MediaId><![CDATA[" + media_id + "]]></MediaId>";
        xml += "<Title><![CDATA[" + title + "]]></Title>";
        xml += "<Description><![CDATA[" + description + "]]></Description>";
        xml += "</Video>";
        xml += "</xml>";
        return xml;
    }

    /**
     * 音乐消息
     *
     * @param map
     * @param title        可选 音乐的标题
     * @param description  可选 音乐的描述
     * @param MUSIC_Url    可选 音乐的链接
     * @param HQ_MUSIC_Url 可选 高音质音乐链接
     * @param media_id     缩略图
     * @return
     */
    public static String music(Map<String, String> map, String title, String description, String MUSIC_Url, String HQ_MUSIC_Url, String media_id) {
        String xml = "";
        //media_id 通过素材管理中的接口上传多媒体文件，得到的id
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[music]]></MsgType>";
        xml += "<Music>";
        xml += "<Title><![CDATA[" + title + "]]></Title>";
        xml += "<Description><![CDATA[" + description + "]]></Description>";
        xml += "<MusicUrl><![CDATA[" + MUSIC_Url + "]]></MusicUrl>";
        xml += "<HQMusicUrl><![CDATA[" + HQ_MUSIC_Url + "]]></HQMusicUrl>";
        xml += "<ThumbMediaId><![CDATA[" + media_id + "]]></ThumbMediaId>";
        xml += "</Music>";
        xml += "</xml>";
        return xml;
    }

    /**
     * ArticleCount	是	图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
     * Articles	是	图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
     * Title	是	图文消息标题
     * Description	是	图文消息描述
     * PicUrl	是	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * Url	是	点击图文消息跳转链接
     *
     * @param map
     * @return
     */
    public static String news(Map<String, String> map, String ArticleCount, String Title, String Description, String PicUrl, String Url) {
        String xml = "";
        //media_id 通过素材管理中的接口上传多媒体文件，得到的id
        xml += "<xml>";
        xml += "<ToUserName><![CDATA[" + map.get("FromUserName") + "]]></ToUserName>";
        xml += "<FromUserName><![CDATA[" + map.get("ToUserName") + "]]></FromUserName>";
        xml += "<CreateTime>" + System.currentTimeMillis() / 1000 + "</CreateTime>";
        xml += "<MsgType><![CDATA[news]]></MsgType>";
        xml += "<ArticleCount>" + ArticleCount + "</ArticleCount>";
        xml += "<Articles>";
        xml += "<item>";
        xml += "<Title><![CDATA[" + Title + "]]></Title>";
        xml += "<Description><![CDATA[" + Description + "]]></Description>";
        xml += "<PicUrl><![CDATA[" + PicUrl + "]]></PicUrl>";
        xml += "<Url><![CDATA[" + Url + "]]></Url>";
        xml += "</item>";
        xml += "</Articles>";
        xml += "</xml>";
        return xml;
    }
}
