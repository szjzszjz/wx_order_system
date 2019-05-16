package com.szjz.sell.configuration;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szjz
 * @date 2019/5/15 20:00
 * 微信开放平台配置
 */

@Configuration
public class WechatOpenConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService WxOpenService(){
        WxMpServiceImpl wxOpenService = new WxMpServiceImpl();
        wxOpenService.setWxMpConfigStorage(getWxMpConfigStorage());
        return wxOpenService;
    }

    @Bean
    public WxMpConfigStorage getWxMpConfigStorage(){
        WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpInMemoryConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
        wxMpInMemoryConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
        return wxMpInMemoryConfigStorage;
    }
}
