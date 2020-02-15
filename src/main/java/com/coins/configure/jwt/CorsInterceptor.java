package com.coins.configure.jwt;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//自定义拦截器
import org.springframework.web.servlet.HandlerInterceptor;
/**
 *  主要为了解决跨域访问问题，在权限拦截器中重定向到errlog接口时会发生跨域访问。
 * @author dyl
 *
 */
public class CorsInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception{

    String origin = httpServletRequest.getHeader("Origin");
    httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
    httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
    httpServletResponse.setHeader("Access-Control-Allow-Headers","Origin,Content-Type,Accept,token,X-Requested-With,authorization");
    httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

    return true;
    }
}