package com.szjz.sell.service.impl;

import com.szjz.sell.configuration.properties.WechatAccountConfigProperty;
import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.service.WXPushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 微信消息推送实现类
 * @author szjz
 * @date 2019/5/17 9:57
 */

@Service
@Slf4j
public class WXPushMessageServiceImpl implements WXPushMessageService {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfigProperty wechatAccountConfigProperty;

    @Override
    public void orderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
        wxMpTemplateMessage.setTemplateId(wechatAccountConfigProperty.getTemplateIdMap().get("orderStatus"));
//        wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
        wxMpTemplateMessage.setToUser("oIZnr5tUVD2JnVrFja764g0xOEvo");
        //oIZnr5tUVD2JnVrFja764g0xOEvo


        List<WxMpTemplateData> data = Arrays.asList(
                new WxMpTemplateData("first","请记得收货"),
                new WxMpTemplateData("keyword1","微信点餐"),
                new WxMpTemplateData("keyword2","18888888888"),
                new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
                new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
                new WxMpTemplateData("keyword5","欢迎下次光临！")
        );
        wxMpTemplateMessage.setData(data);
        try{
            wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
        }catch (WxErrorException e){
            log.error("【微信模板消息】 发送失败 {}",e);
        }
    }
}
