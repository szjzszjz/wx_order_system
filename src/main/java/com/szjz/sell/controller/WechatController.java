package com.szjz.sell.controller;

import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.resultObject.ResultObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.catalina.util.URLEncoder;
import org.apache.tomcat.util.buf.Utf8Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

/**
 * @author szjz
 * @date 2019/5/10 14:52
 * 微信网页授权
 */

@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    @ApiOperation(value = "微信授权", notes = "最终的目的是为了获取用户的openID", response = ResultObject.class)
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //配置
        //调用方法
        log.info("【微信网页授权】 开始授权，，，returnUrl={}",returnUrl);
        String url = "http://szjz.natapp1.cc/sell/wechat/userInfo";
        url = URLEncoder.DEFAULT.encode(url, Charset.forName("utf-8"));
        String encodeUrl = URLEncoder.DEFAULT.encode(returnUrl, Charset.forName("utf-8"));
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, encodeUrl);
        return "redirect:"+ redirectUrl;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String  userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            e.printStackTrace();
            log.error("【微信网页授权】 {}",e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】 成功！openid={}",openId);
        return "redirect:"+returnUrl + "?openid=" + openId;
    }


}
