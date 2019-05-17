<#--添加弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                您有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="detailBtn" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--添加音乐-->
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<#--消息推送-->
<script>
    var websocket = null;
    if ('WebSocket' in window) {
        websocket = new WebSocket('ws://szjz.natapp1.cc/sell/webSocket');
    } else {
        alert('该浏览器不支持websocket!')
    }

    websocket.onopen = function (event) {
        console.log('建立连接')
    }

    websocket.onclose = function (event) {
        console.log('断开连接')
    }

    websocket.onmessage = function (event) {
        console.log('收到消息：' + event.data);
        //弹窗消息 播放音乐
        $('#myModal').modal('show');
        document.getElementById('notice').play();

        //查看订单详情
        $('#detailBtn').click(function () {
            location.href='/sell/seller/order/detail?orderId=' + event.data;
        })

    }

    websocket.onerror = function(){
        alert('websocket通信发生错误');
    }

    window.onbeforeunload = function () {
        websocket.close();
    }

</script>