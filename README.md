# wx_order_system
## 微信点餐系统

## 系统要求：  
Linux：centos7.3  
jdk:1.8.0  
mysql:5.7.17  
nginx:1.11.7  
redis:3.2.8  
springboot：1.5.2  
idea:2017.1.2  
maven:3.3.9  

* 虚拟机系统：centos7.3  
账号：root  
密码：123456  

* 包括的软件  
jdk 1.8.0.11   
nginx 1.11.7  
mysql 5.7.17  
redis 3.2.8  

* jdk  
路径 /usr/local/jdk1.8.0  

* nginx  
路径 /usr/local/nginx  
启动 nginx  
重启 nginx -s reload  

* mysql  
配置 /etc/myconf  
账号 root  
密码 123456  
端口 3306  
启动 systemctl start mysql  
停止 systemctl stop mysql  

* redis  
路径 /usr/local/redis  
配置 /etc/reis.conf  
端口 6379  
密码 123456  
启动 systemctl start redis  
停止 systemctl stop redis  

* 微信网页授权  
官方文档
https://mp.weixin.qq.com/wiki  
调试工具
https://natapp.cn  
第三方SDK
https://github.com/Wechat-Group/WxJava  

* 微信支付  
官方文档：
https://pay.weixin.qq.com/wiki  
第三方SDK:
https://github.com/Pay-Group/best-pay-sdk  

* 微信开放平台  
https://open.weixin.qq.com/  

## 知识点
### 1.websocket  
```text
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```
[前端websocket](https://github.com/szjzszjz/wx_order_system/blob/master/src/main/resources/templates/common/websocket.ftl)  
[后台websocket](https://github.com/szjzszjz/wx_order_system/blob/master/src/main/java/com/szjz/sell/service/WebSocket.java)  
后台出发websocket向前端发送信息  
```text
//用户下单成功调用websocket消息推送
webSocket.sendMessage(orderDTO.getOrderId());
```

### [数据库表格](https://github.com/szjzszjz/wx_order_system/blob/master/src/main/resources/static/sql/sell.sql)

