package com.szjz.sell.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author szjz
 * @date 2019/5/10 15:19
 * 微信账户配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {

    /** 公众号开发者id */
    private String mpAppId;

    /** 秘钥 */
    private String mpAppSecret;

    /** 商户号 */
    private String mchId;

    /** 商户秘钥 */
    private String mchKey;
    
    /** 商户证书路径 */
    private String keyPath;

    /** 微信支付异步通知地址 */
    private String notifyUrl;

}
