package com.szjz.sell.configuration;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.szjz.sell.configuration.properties.WechatAccountConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szjz
 * @date 2019/5/13 14:42
 * <p>
 * 微信支付配置
 */

@Configuration
public class WechatPayConfig {

    @Autowired
    private WechatAccountConfigProperty wechatAccountConfigProperty;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfigProperty.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfigProperty.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfigProperty.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfigProperty.getMchKey());
        wxPayH5Config.setKeyPath(wechatAccountConfigProperty.getKeyPath());
        wxPayH5Config.setNotifyUrl(wechatAccountConfigProperty.getNotifyUrl());
        return wxPayH5Config;
    }
}
