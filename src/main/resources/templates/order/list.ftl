<!doctype html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--    边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--    主要内容content-->
    <div id="page-content-wrapper">
        <#--        流动布局container-fluid-->
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>订单id</th>
                            <th>姓名</th>
                            <th>手机号/th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付方式/th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDTOPage.getContent() as orderDTO>
                            <tr class="message-success">
                                <td>${orderDTO.getOrderId()}</td>
                                <td>${orderDTO.getBuyerName()} </td>
                                <td>${orderDTO.getBuyerPhone()} </td>
                                <td>${orderDTO.getBuyerAddress()}</td>
                                <td>${orderDTO.getOrderAmount()} </td>
                                <td>${orderDTO.getOrderStatusEnum().getMessage()}</td>
                                <td>微信支付</td>
                                <td>${orderDTO.getPayStatusEnum().getMessage()} </td>
                                <td>${orderDTO.getCreateTime()}</td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}">详情</a></td>
                                <td>
                                    <#if orderDTO.getOrderStatusEnum().getMessage() == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <#if currentPage == 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?pageNum=${currentPage-1}&pageSize=${pageSize}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage== index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li>
                                    <a href="/sell/seller/order/list?pageNum=${index}&pageSize=${pageSize}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage==orderDTOPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li>
                                <a href="/sell/seller/order/list?pageNum=${currentPage + 1}&pageSize=${pageSize}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#--引入websocket-->
<#include "../common/websocket.ftl">

</body>
</html>