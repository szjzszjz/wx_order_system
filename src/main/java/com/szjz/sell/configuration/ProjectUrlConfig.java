package com.szjz.sell.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author szjz
 * @date 2019/5/15 20:19
 *
 * 项目中包含的url 需要在yml中配置
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /** 微信公众平台授权url */
    private String wechatMpAuthorize;

    /** 微信开放平台授权URL */
    private String wechatOpenAuthorize;

    /** 点餐系统 */
    private String sell;

}

