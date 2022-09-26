package com.li.javazeromofish.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class LogCostInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        StringBuilder sb = new StringBuilder(1000);

        String url =request.getRequestURL().toString();
        if(!url.contains(".css") && !url.contains(".js") && !url.contains(".png") &&  !url.contains(".jpg")) {
            System.out.println("*******************访问地址："+url+"*******************");
            System.out.println("*******************客户端请求地址和操作端口：" + request.getRemoteAddr() + ":" + request.getRemotePort() + "*******************");
        }

        //获取请求参数
        Enumeration em = request.getParameterNames();
        JSONObject data = new JSONObject();
        while (em.hasMoreElements()) {
            String name = (String) em.nextElement();
            String value = request.getParameter(name);
            data.put(name,value);
        }

//        Map<String, String> map = XMLUtils.xmlToMap(request);

        HandlerMethod h = null;
        try {
            h = (HandlerMethod) handler;
        } catch (Exception e) {
            return true;
        }
        sb .append("-------------------------------------------------------------\n");
        sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
        sb.append("Method    : ").append(h.getMethod().getName()).append("\n");
        sb.append("Params    : ").append(data).append("\n");
//        sb.append("Body      : ").append(map).append("\n");
        sb.append("URI       : ").append(request.getRequestURI()).append("\n");
        sb.append("URL       : ").append(request.getRequestURL()).append("\n");
        sb .append("-------------------------------------------------------------\n");
        System.out.println(sb.toString());
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
 
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}