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
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" class="form-control" name="productName" required
                                   value="${(productInfo.getProductName())!""}"/>
                        </div>
                        <div class="form-group">
                            <label>价格</label>
                            <input type="number" class="form-control" name="productPrice" required
                                   value="${(productInfo.getProductPrice())!""}"/>
                        </div>
                        <div class="form-group">
                            <label>库存</label>
                            <input type="number" class="form-control" name="productStock" required
                                   value="${(productInfo.getProductStock())!""}"/>
                        </div>
                        <div class="form-group">
                            <label>描述</label>
                            <input type="text" class="form-control" name="productDescription"
                                   value="${(productInfo.getProductDescription())!""}"/>
                        </div>
                        <div class="form-group">
                            <label>图片</label><br>
                            <img height="200" width="200" src="${(productInfo.getProductIcon())!""}"><br>
                            <input type="text" class="form-control" name="productIcon" required
                                   value="${(productInfo.getProductIcon())!""}" onblur="do {
                                        console.valueOf("fffff")
                                    }"/>
                        </div>

                        <div class="form-group">
                            <label>类目</label>
                            <select name="categoryType" class="form-control" >
                                <#list productCategoryList as category>
                                    <option value="${category.getCategoryType()}"
                                            <#--对selected进行判断性选择 如果类目存在 且相等 才为选择状态-->
                                            <#if (productInfo.getCategoryType())?? && productInfo.getCategoryType()==category.getCategoryType()>
                                                selected
                                            </#if>
                                    >
                                        ${category.getCategoryName()}
                                    </option>
                                </#list>
                            </select>
                        </div>

                        <input hidden type="text"  name="productId" value="${(productInfo.getProductId())!""}">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<#include "../common/websocket.ftl">

</body>
</html>