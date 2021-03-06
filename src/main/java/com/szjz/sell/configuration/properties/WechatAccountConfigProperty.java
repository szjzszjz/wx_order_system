package com.szjz.sell.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author szjz
 * @date 2019/5/10 15:19
 * 微信账户配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfigProperty {

    /**
     * 公众号开发者id
     */
    private String mpAppId;

    /**
     * 公众号开发者秘钥
     */
    private String mpAppSecret;

    /**
     * 维信开放平台id
     */
    private String openAppId;

    /**
     * 维信开放平台秘钥
     */
    private String openAppSecret;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户秘钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 消息推送的模板id
     */
    private Map<String, String> templateIdMap;

}
