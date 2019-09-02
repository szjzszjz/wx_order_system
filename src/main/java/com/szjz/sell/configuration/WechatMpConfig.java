package com.szjz.sell.configuration;

import com.szjz.sell.configuration.properties.ProjectUrlCofigProperty;
import com.szjz.sell.configuration.properties.WechatAccountConfigProperty;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szjz
 * @date 2019/5/10 14:55
 * 微信公众号配置
 */

@Configuration
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfigProperty wechatAccountConfigProperty;

    @Bean
    public WxMpService wxMpService() {
        WxMpServiceImpl wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechatAccountConfigProperty.getMpAppId());
        wxMpConfigStorage.setSecret(wechatAccountConfigProperty.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
