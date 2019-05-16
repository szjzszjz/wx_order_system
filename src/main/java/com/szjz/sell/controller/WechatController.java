package com.szjz.sell.controller;

import com.szjz.sell.configuration.ProjectUrlConfig;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.utils.URLEncoderUtil;
import io.swagger.annotations.Api;
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
    private WxMpService wxMpService ;

    private ProjectUrlConfig projectUrlConfig;

    /**
     * 微信授权过程：先判断前端请求的时候cookies是否有携带openID
     * 如果没有会跳转到前端配置的openidUrl 对应的请求路径
     * 就是以下方法对应的授权路径 从而获取openid
     * @param returnUrl
     * @return
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    @ApiOperation(value = "微信网页授权", notes = "最终的目的是为了获取用户的openID", response = ResultObject.class)
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //配置
        //调用方法
        log.info("【微信网页授权】 开始授权 returnUrl={}",returnUrl);
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        url = URLEncoderUtil.encoderUTF8(url);
        returnUrl = URLEncoderUtil.encoderUTF8(returnUrl);
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, returnUrl);
        log.info("【微信网页授权】 正在授权 redirectUrl={}",redirectUrl);
        return "redirect:"+ redirectUrl;
    }

    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ApiOperation(value = "此方法是微信授权跳转的方法 不用单独测试")
    public String  userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            log.info("【微信网页授权】 正在授权 code={}",code);
        }catch (WxErrorException e){
            e.printStackTrace();
            log.error("【微信网页授权】 异常exception={}",e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】 成功授权 openid={}",openId);
        return "redirect:"+returnUrl + "?openid=" + openId;
    }


    /**
     * 二维码扫码登录
     * 授权
     * @param returnUrl
     * @return
     */
    @RequestMapping(value = "/qrAuthorize", method = RequestMethod.GET)
    @ApiOperation(value = "微信开放平台授权", notes = "最终的目的是为了获取用户的openID", response = ResultObject.class)
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        //配置
        //调用方法
        log.info("【微信开放平台授权】 开始授权 returnUrl={}",returnUrl);
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        url = URLEncoderUtil.encoderUTF8(url);
        returnUrl = URLEncoderUtil.encoderUTF8(returnUrl);
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, returnUrl);
        log.info("【微信开放平台授权】 正在授权 redirectUrl={}",redirectUrl);
        return "redirect:"+ redirectUrl;
    }

    @RequestMapping(value = "/qrUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "此方法是微信开放平台二维码授权跳转的方法 不用单独测试")
    public String  qrUserInfo(@RequestParam("code") String code,
                            @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            log.info("【微信开放平台授权】 正在授权 code={}",code);
        }catch (WxErrorException e){
            e.printStackTrace();
            log.error("【微信开放平台授权】 异常exception={}",e);
            throw new SellException(ResultEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信开放平台授权】 成功授权 openid={}",openId);
        return "redirect:"+returnUrl + "?openid=" + openId;
    }
}
