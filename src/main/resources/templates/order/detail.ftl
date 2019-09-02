<!doctype html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--    边栏sidebar-->

    <#include "../common/nav.ftl">

    <div class="container">
        <div class="row clearfix">

            <#--        订单详情-->
            <div class="col-md-6 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>订单编号</th>
                        <th>订单总金额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${orderDTO.getOrderId()}</td>
                        <td>${orderDTO.getOrderAmount()}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <#--        商品订单列表-->
            <div class="col-md-12 column">
                <table class="table table-hover table-striped">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>总额</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list  orderDTO.getOrderDetailList() as orderDetail>
                        <tr>
                            <td>${orderDetail.getProductId()}</td>
                            <td>${orderDetail.getProductName()}</td>
                            <td>${orderDetail.getProductPrice()}</td>
                            <td>${orderDetail.getProductQuantity()}</td>
                            <td>${orderDetail.getProductQuantity() * orderDetail.getProductPrice()}</td>
                        </tr>
                    </#list>

                    </tbody>
                </table>
            </div>

            <#--        按鈕-->
            <div class="col-md-12 column">
                <#if orderDTO.getOrderStatus() == 0>
                    <a href="/sell/seller/order/finish?orderId=${orderDTO.getOrderId()}" type="button"
                       class="btn btn-default btn-primary">完结订单</a>
                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}" type="button"
                       class="btn btn-default btn-danger">取消订单</a>
                </#if>
            </div>
        </div>
    </div>
</div>


<#include "../common/websocket.ftl">
</body>
</html>
