package com.szjz.sell.controller;

import com.szjz.sell.configuration.properties.ProjectUrlCofigProperty;
import com.szjz.sell.constant.CookieConstant;
import com.szjz.sell.constant.RedisConstant;
import com.szjz.sell.dataobject.SellerInfo;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.service.SellerInfoService;
import com.szjz.sell.utils.CookieUtil;
import com.szjz.sell.utils.JsonUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author szjz
 * @date 2019/5/16 10:29
 * 卖家用户
 */

@Slf4j
@Controller
//@RequestMapping("/")
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlCofigProperty projectUrlCofigProperty;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("login/index");
    }

    @GetMapping("/seller/user/login")
    @ApiOperation(value = "卖家用户登录")
    public ModelAndView login(@RequestParam(required = false) String openid,
                              @RequestParam String username,
                              @RequestParam String password,
                              HttpServletResponse response,
                              HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //1、传入的openid去和数据库中的openid进行校验
        //SellerInfo sellerInfo = sellerInfoService.findSellerInfoByOpenid(openid);
        SellerInfo sellerInfo = sellerInfoService.findByUsernameAndPassword(username, password);

        if (sellerInfo == null) {
            log.error("【用户登录】 username={},password={},{} ", username, password, ResultEnum.LOGIN_ERROR_USER_NOT_EXIST.getMessage());
            map.put("msg", ResultEnum.LOGIN_ERROR_USER_NOT_EXIST.getMessage());
            map.put("url", "/sell/seller/user/index");
            return new ModelAndView("common/error", map);
        }

        //2、设置token至Redis
        String token = UUID.randomUUID().toString();//uuid生成token
        Integer expire = RedisConstant.EXPIRE;//过期时间
        // 参数 1 以token_开头的key值  2 value = openid  3 过期时间  4 时间单位
        //openID登录
        //stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        //密码登录
        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put("username", username);
        loginMap.put("password", password);
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), JsonUtil.toJson(loginMap), expire, TimeUnit.SECONDS);


        //3、设置token至客户端的cookis 每次登录就会携带上一次的服务器返回的token
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);

        return new ModelAndView("redirect:" + projectUrlCofigProperty.getSell() + "/sell/seller/order/list");
    }


    @GetMapping("/seller/user/logout")
    @ApiOperation(value = "卖家用户退出")
    public ModelAndView logout(HttpServletResponse response,
                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //从cookie中查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.error("【用户登出】 {}", ResultEnum.LOGOUTED.getMessage());
            map.put("msg", ResultEnum.LOGOUTED.getMessage());
            map.put("url", "/sell/seller/user/login");
            return new ModelAndView("common/error", map);
        }

        if (cookie != null) {
            //清楚redis
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            //清楚cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        log.info("【用户登出】 {}", ResultEnum.LOGOUT_SUCCESS.getMessage());
        return new ModelAndView("login/index");
    }


}
