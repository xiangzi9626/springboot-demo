package com.example.demo.weixin.mp;

import com.example.demo.util.HttpUtil;
import com.example.demo.weixin.Config;
import javafx.print.Printer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 网页授权
 */
@WebServlet("/wxoauth")
public class OAuthServlet extends HttpServlet {
    private static final String APPID = Config.MpAPPID;
    /*public static void getCode(){
        String url="";
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scope="snsapi_userinfo";
        //用户点击确定跳转的地址 微信会带上code参数 通过code获取access_token;
        String REDIRECT_URI="http://"+req.getServerName()+"/wxoauth";
        String url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri="+REDIRECT_URI+"&response_type=code&scope="+scope+"&state=STATE#wechat_redirect";
       if (req.getParameter("code")==null){
        resp.sendRedirect(url);
        return;
       }else{
           String code=req.getParameter("code");
         String wxUser=OauthAccessToken.getWxUserInfo(code);
         System.out.print("\n"+wxUser);
           PrintWriter out=resp.getWriter();
           out.print(wxUser);
           out.flush();
           out.close();
       }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
