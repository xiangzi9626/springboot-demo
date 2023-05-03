package com.example.demo.weixin.mp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

/**
 * 微信服务器发送请求到开发者服务器,验证成功成为微信开发者
 * signature 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
 * timestamp 时间戳
 * nonce	随机数
 * echostr	随机字符串
 */
@WebServlet("/wx")
public class WxServlet extends HttpServlet {
    private String token = "token";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
       //接收微信服务器发来的xml
        List list = new ArrayList();
        Enumeration<String> params = req.getParameterNames();
        for (Enumeration<String> e = params; e.hasMoreElements();) {
            String key = e.nextElement().toString();
            list.add(key+"-"+req.getParameter(key));
        }
       System.out.print(list);
        */
        String signature = req.getParameter("signature");
        String echostr = req.getParameter("echostr");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        if (check(token, signature, timestamp, nonce)) {
            PrintWriter out = resp.getWriter();
            out.print(echostr);
            out.flush();
            out.close();
            System.out.print("接入成功");
        } else {
            System.out.print("接入失败");
        }
    }

    public Boolean check(String token, String signature, String timestamp, String nonce) {
        //1 将token timestamp nonce 进行字典序排序
        String[] strs = new String[]{token, timestamp, nonce};
        Arrays.sort(strs);
        //2 将三个字符串拼成一个字符串进行sha1加密
        String str = strs[0] + strs[1] + strs[2];
        String mysig = sha1(str);
        //3 开发者拿到加密后的字符串与signature进行对比标识该请法来自微信服务器
        return mysig.equalsIgnoreCase(signature);
    }

    /**
     * 进行sha1加密
     *
     * @param src
     * @return
     */
    public String sha1(String src) {
        try {
            //获取一个加密对象
            MessageDigest md = MessageDigest.getInstance("sha1");
            //加密
            byte[] digest = md.digest(src.getBytes());
            char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder sb = new StringBuilder();
            //处理加密结果
            for (byte b : digest) {
                sb.append(chars[(b >> 4) & 15]);
                sb.append(chars[b & 15]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 接收微信用户消息和事件推送
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* ServletInputStream is=req.getInputStream();
        byte[] b=new byte[1024];
        int len;
        StringBuilder sb=new StringBuilder();
        while ((len=is.read(b))!=-1){
            sb.append(new String(b,0,len));
        }*/
        //处理消息和事件推送
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        Map<String, String> map = Message.xmlToMap(req.getInputStream());
        //准备回复的数据包
        //回复文字消息
        String textMsg = Message.text(map, "你好民办");
        PrintWriter out = resp.getWriter();
        /*out.print(textMsg);
        out.flush();
        out.close();*/
        //获取图片素材
        /*String material=Material.getMaterialList("image","0","20");
        Gson gson=new Gson();
        Map<String, List<Map<String,Object>>> mp=gson.fromJson(material,Map.class);
        String media_id=mp.get("item").get(0).get("media_id").toString();*/
        //获取音频素材
        /*String material=Material.getMaterialList("voice","0","20");
         Gson gson=new Gson();
        Map<String, List<Map<String,Object>>> mp=gson.fromJson(material,Map.class);
         String media_id=mp.get("item").get(0).get("media_id").toString();*/
        //获取视频素材
        /*String material=Material.getMaterialList("video","0","20");
        Gson gson=new Gson();
        Map<String, List<Map<String,Object>>> mp=gson.fromJson(material,Map.class);
        String media_id=mp.get("item").get(0).get("media_id").toString();
        String title=mp.get("item").get(0).get("name").toString();
        String description=mp.get("item").get(0).get("description").toString();*/
        //获取图文素材
        /*String material=Material.getMaterialList("news","0","20");
        Gson gson=new Gson();
        Map<String, List<Map<String,Object>>> mp=gson.fromJson(material,Map.class);
        Map<String,List<Map<String,Object>>> content= (Map<String, List<Map<String, Object>>>) mp.get("item").get(0).get("content");
        String title=content.get("news_item").get(0).get("title").toString();
        String thumb_url=content.get("news_item").get(0).get("thumb_url").toString();
        String url=content.get("news_item").get(0).get("url").toString();*/
         System.out.print("");
        //回复图片消息
        // String imgMsg=Message.image(map,media_id);
        //回复语音消息
        //String msg=Message.voice(map,media_id);
        //回复音乐消息
        //String msg=Message.music(map,"酷我音乐","","","http://www.kuwo.cn/play_detail/141163675",media_id);
        //回复视频消息
        //String msg=Message.video(map,media_id,title,description);
        //回复图文消息
       /* String msg=Message.news(map,"1",title,"",thumb_url,url);
       out.print(msg);
        out.flush();
        out.close();*/
        //删除素材
        /*String del=Material.delMaterial(media_id);
        System.out.print(del);*/
        //设置模板消息行业
       //String set=TemplateMsg.setIndustry();
        //获取模板消息行业
        //String get=TemplateMsg.getIndustry();
        //生成带参数的二维码
       /* String url=Account.createForeverTicket(1000);
        System.out.print(url);
        QrCodeUtil.generate(url, 300, 300, FileUtil.file("D:/weixin/wxOrcode.jpg"));*/
        //获取已关注用户列表
        //String users=User.getUserList("");
        //通过openID获取用户信息
        //String userInfo=User.getUserOne("ocMvMwSuxXEu1rTsm9TCC2VspU94");
        //System.out.print(userInfo);
        //设置菜单
        Menu.setMenu();
    }
}
