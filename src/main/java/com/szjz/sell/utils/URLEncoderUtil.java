package com.szjz.sell.utils;

import org.apache.catalina.util.URLEncoder;

import java.nio.charset.Charset;

/**
 * @author szjz
 * @date 2019/5/16 10:11
 * url编码工具
 */
public class URLEncoderUtil {

    public static String encoderUTF8(String url) {
        return URLEncoder.DEFAULT.encode(url, Charset.forName("utf-8"));
    }
}
