# wx_order_system
微信点餐系统

* 系统要求：  
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



* 表格：  
```
create table `product_info` (
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '单价',
	`product_stock` int not null comment '库存',
	`product_description` varchar(64) comment '描述',
	`product_icon` varchar(512) comment '小图',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`product_id`)
)comment '商品表';


create  table `product_category` (
	`catetory_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '类目名称',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`catetory_id`),
	unique key `uqe_category_type` (`category_type`)
)comment '类目表';	


create table `order_master` (
	`order_id` varchar(32) not null,
	`buyer_name` varchar(32) not null comment '买家名字',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_adress` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态，默认0新下单',
	`pay_status` tinyint(3) not null default '0' comment '支付状态，默认0未支付',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
)comment '订单表';

create table `order_detail` (
	`detail_id` varchar(32) not null,
	`order_id` varchar(32) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '商品价格',
	`product_quantity` int not null comment '商品数量',
	`product_icon` varchar(512) not null comment '商品小图',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`) comment '索引'
 )comment '订单详情表';
 ```
