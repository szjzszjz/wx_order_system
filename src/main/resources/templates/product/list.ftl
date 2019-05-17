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
                            <th>商品编号</th>
                            <th>名称</th>
                            <th>图片</th>
                            <th>单价</th>
                            <th>库存</th>
                            <th>描述</th>
                            <th>介绍</th>
                            <th>类目</th>
                            <th>创建时间</th>
                            <th>修改时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list productInfoPage.getContent() as productInfo>
                            <tr class="message-success">
                                <td>${productInfo.getProductId()}</td>
                                <td>${productInfo.getProductName()}</td>
                                <td><img height="100" width="100" src="${productInfo.getProductIcon()}"></td>
                                <td>${productInfo.getProductPrice()}</td>
                                <td>${productInfo.getProductStock()}</td>
                                <td>${productInfo.getProductDescription()}</td>
                                <td>介绍</td>
                                <td>${productInfo.getCategoryType()}</td>
                                <td>${productInfo.createTime}</td>
                                <td>${productInfo.updateTime}</td>
                                <td><a href="/sell/seller/product/index?productId=${productInfo.getProductId()}">修改</a>
                                </td>
                                <td>
                                    <#if productInfo.getProductStatusEnum().getMessage()=="上架">
                                        <a href="/sell/seller/product/offSale?productId=${productInfo.getProductId()}">下架</a>
                                    <#else >
                                        <a href="/sell/seller/product/onSale?productId=${productInfo.getProductId()}">上架</a>
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
                            <li>
                                <a href="/sell/seller/product/list?pageNum=${currentPage-1}&pageSize=${pageSize}">上一页</a>
                            </li>
                        </#if>

                        <#list 1..productInfoPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else >
                                <li>
                                    <a href="/sell/seller/product/list?pageNum=${index}&pageSize=${pageSize}">${index}</a>
                                </li>
                            </#if>
                        </#list>

                        <#if currentPage==productInfoPage.getTotalPages()>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else >
                            <li>
                                <a href="/sell/seller/product/list?pageNum=${currentPage + 1}&pageSize=${pageSize}">下一页</a>
                            </li>
                        </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/websocket.ftl">

</body>
</html>