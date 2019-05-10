package com.szjz.sell.controller;

import com.szjz.sell.resultObject.ResultObject;
import com.szjz.sell.utils.ResultObjectUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author szjz
 * @date 2019/5/10 11:43
 * 微信授权
 * https://open.weixin.qq.com/connect/oauth2/authorize
 * ?appid=wx361556cc26ac4671
 * &redirect_uri=http://szjz.natapp1.cc/sell/weixin/auth
 * &response_type=code
 * &scope=snsapi_base
 * &state=STATE#wechat_redirect
 */

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    @ApiOperation(value = "微信授权", notes = "", response = ResultObject.class)
    public void auth(@RequestParam(value = "code",required = false) String code) {
        log.info("【微信授权】 开始。。。。。。。");

        log.info("code={}", code);
    }
}
