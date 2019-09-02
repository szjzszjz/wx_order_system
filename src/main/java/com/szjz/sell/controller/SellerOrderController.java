package com.szjz.sell.controller;

import com.szjz.sell.dto.OrderDTO;
import com.szjz.sell.enums.ResultEnum;
import com.szjz.sell.exception.SellException;
import com.szjz.sell.service.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author szjz
 * @date 2019/5/14 14:06
 * 卖家订单
 */

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 订单列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页的数量
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "订单列表", notes = "所有用户")
    public ModelAndView list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        //默认从第零页开始查
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", pageNum);
        map.put("pageSize", pageSize);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    @ApiOperation(value = "商家取消订单")
    public ModelAndView cancel(@RequestParam String orderId) {
        Map<String, Object> map = new HashMap<>();
        OrderDTO orderDTO;
        //如果有异常捕获抛出的异常，并跳到异常界面打印错误信息
        try {
            orderDTO = orderService.findById(orderId);
            orderService.cancelOrder(orderDTO);
        } catch (SellException e) {
            e.printStackTrace();
            map.put("title", "订单取消异常");
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }

    @GetMapping("/finish")
    @ApiOperation(value = "商家完结订单")
    public ModelAndView finish(@RequestParam String orderId) {
        Map<String, Object> map = new HashMap<>();
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findById(orderId);
            orderService.finishOrder(orderDTO);
        } catch (SellException e) {
            e.printStackTrace();
            map.put("title", "订单完结异常");
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("order/error", map);
        }
        map.put("msg", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "商家订单详情")
    public ModelAndView detail(@RequestParam String orderId) {
        Map<String, Object> map = new HashMap<>();
        OrderDTO orderDTO;
        try {
            orderDTO = orderService.findById(orderId);
        } catch (SellException e) {
            e.printStackTrace();
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("orderDTO", orderDTO);
        return new ModelAndView("order/detail", map);

    }


}
