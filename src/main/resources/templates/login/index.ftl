<!doctype html>
<html lang="en">
<#include "../common/header.ftl">

<style>
    .container-fluid{

        width: auto;
        height: max-content;
        background:url("https://img14.360buyimg.com/n0/jfs/t1/26809/3/12717/61505/5c9a3b9bE6bd98afc/a91cbfd8e1907aea.jpg");
    }

</style>
<body>
<div class="container-fluid">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <form role="form" method="get" action="/sell/seller/user/login">
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" class="form-control" name="username" required />
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" class="form-control" name="password" required/>
                </div>

                 <button type="submit" class="btn btn-default" >登录</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>