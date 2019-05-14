<!doctype html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
    <#--    边栏sidebar-->
    <#--    <#include "../common/nav.ftl">-->

    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <ul class="nav sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    卖家管理系统
                </a>
            </li>
            <li>
                <a href="/seller/order/list"><i class="fa fa-fw fa-list-alt"></i> 订单</a>
            </li>
            <li class="dropdown open">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
                            class="fa fa-fw fa-plus"></i> 商品 <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">操作</li>
                    <li><a href="/seller/product/list">列表</a></li>
                    <li><a href="/seller/product/index">新增</a></li>
                </ul>
            </li>
            <li class="dropdown open">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true"><i
                            class="fa fa-fw fa-plus"></i> 类目 <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li class="dropdown-header">操作</li>
                    <li><a href="/seller/category/list">列表</a></li>
                    <li><a href="/seller/category/index">新增</a></li>
                </ul>
            </li>

            <li>
                <a href="/seller/logout"><i class="fa fa-fw fa-list-alt"></i> 登出</a>
            </li>
        </ul>
    </nav>
    <#--    主要内容content-->
    <div id="page-content-wrapper">
        <#--        流动布局container-fluid-->
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th> 订单id</th>
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
                                <td>${orderDTO.getOrderId()}
                                </td>
                                <td>${orderDTO.getBuyerName()}
                                </td>
                                <td>${orderDTO.getBuyerPhone()}
                                </td>
                                <td>${orderDTO.getBuyerAddress()}
                                </td>
                                <td>${orderDTO.getOrderAmount()}
                                </td>
                                <td>${orderDTO.getOrderStatusEnum(orderDTO.getOrderStatus()).getMessage()}
                                </td>
                                <td>微信支付
                                </td>
                                <td>${orderDTO.getPayStatusEnum(orderDTO.getPayStatus()).getMessage()}
                                </td>
                                <td>${orderDTO.getCreateTime()}
                                </td>
                                <td><a href="/sell/seller/order/detail?orderId=${orderDTO.getOrderId()}">详情</a>
                                </td>
                                <td>
                                    <#if orderDTO.getOrderStatus() == 0>
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
                        <#if currentPage == 0>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else >
                            <li><a href="/sell/seller/order/list?pageNum=${currentPage-1}&pageSize=${pageSize}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage + 1== index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li>
                                    <a href="/sell/seller/order/list?pageNum=${index-1}&pageSize=${pageSize}">${index}</a>
                                </li>
                            </#if>
                        </#list>
                        <#if currentPage==orderDTOPage.getTotalPages()-1>
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


</body>
</html>