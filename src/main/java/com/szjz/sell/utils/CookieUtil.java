package com.szjz.sell.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 *
 * @author szjz
 * @date 2019/5/16 11:09
 */


public class CookieUtil {

    /**
     * 设置cookie
     *
     * @param response http响应
     * @param name     cookie键
     * @param value    cookie值
     * @param maxAge   过期时间
     */
    public static void set(HttpServletResponse response,
                           String name,
                           String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    /**
     * 获取cookie
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static Cookie get(HttpServletRequest request,
                             String cookieName) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static Cookie getTest(HttpServletRequest request,
                                 String cookieName) {
        Map<String, Object> map = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                map.put(cookie.getName(), cookie);
            }
        }
        if (map.containsKey(cookieName)) {
            return (Cookie) map.get(cookieName);
        } else {
            return null;
        }
    }

}
