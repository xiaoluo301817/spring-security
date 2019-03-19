package cn.xiaoluo.security.springsecurity.controller;

import cn.xiaoluo.security.springsecurity.properties.BrowserProperties;
import cn.xiaoluo.security.springsecurity.support.SimpleResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author luowenqin
 * @create 2019-03-12 10:51
 */
@RestController
@RequestMapping(value ="/authentication" )
public class RestSecurityController {





    //请求缓存类(存放在当前的session中)
    private RequestCache requestCache =new HttpSessionRequestCache();


    //从定向类
    private RedirectStrategy redirectStrategy =new DefaultRedirectStrategy();



    @Autowired
    private BrowserProperties properties;

    /**
     * 当需要身份认证，则返回当前的代码内
     * 如果是则返回登录页面，否则返回401和错误信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request,
                                                HttpServletResponse response){
        org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());


        try {


            //1.从session中拿到当前的请求信息
            SavedRequest savedRequest =
                        requestCache.getRequest(request,response);

            if (savedRequest !=null){
                //2.获取到当前跳转的URL
                String url = savedRequest.getRedirectUrl();
                logger.info("引发当前跳转的URL是："+url);


                //3、判断当前的url是不是一个html页面请求
                if (StringUtils.endsWithIgnoreCase(url,".html")){
                    redirectStrategy.sendRedirect(request,response,properties.getLoginPage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new  SimpleResponse("访问的" +
                "服务需要身份认证，" +
                "请引导客户到登录页面") ;
    }
}
