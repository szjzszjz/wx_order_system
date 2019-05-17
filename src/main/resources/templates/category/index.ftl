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
                    <form role="form" method="post" action="/sell/seller/category/save">
                        <div class="form-group">
                            <label>名称</label>
                            <input type="text" class="form-control" name="categoryName" required
                                   value="${(productCategory.getCategoryName())!""}"/>
                        </div>
                        <div class="form-group">
                            <label>type</label>
                            <input type="number" class="form-control" name="categoryType" required
                                   value="${(productCategory.getCategoryType())!""}"/>
                        </div>

                        <input hidden type="number"  name="categoryId" value="${(productCategory.getCategoryId())!""}">
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