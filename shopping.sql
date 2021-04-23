-- 创建数据库shopping
drop database if exists shopping;
create database shopping;
use shopping;
-- -----------------------------

-- 1.用户登录表(customer_login)
create table customer_login(
  customer_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '用户ID',
  login_name VARCHAR(20) NOT NULL COMMENT '用户登录名',
  password CHAR(32) NOT NULL COMMENT 'md5加密的密码',
  user_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态', -- 0为正常,1为异常
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (customer_id)
);
/*
所有的修改时间都是记录最后一次的修改时间
*/

-- 2.用户信息表(customer_inf)
create table customer_inf(
  customer_inf_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  customer_id INT UNSIGNED NOT NULL COMMENT 'customer_login表的自增ID',
  customer_true_name VARCHAR(20) NOT NULL COMMENT '用户真实姓名',
  identity_card_type TINYINT NOT NULL DEFAULT 1 COMMENT '证件类型：1 身份证，2 军官证，3 护照',
  identity_card_no VARCHAR(20) COMMENT '证件号码',
  mobile_phone INT UNSIGNED COMMENT '手机号',
  customer_email VARCHAR(50) COMMENT '邮箱',
  gender CHAR(1) COMMENT '性别',
  register_time TIMESTAMP NOT NULL COMMENT '注册时间',
  birthday DATETIME COMMENT '生日',
  user_money DECIMAL(8,2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (customer_inf_id),
  foreign key (customer_id) references customer_login(customer_id)
) ENGINE = innodb COMMENT '用户信息表';

-- 3.用户地址表(customer_addr)
create table customer_addr(
  customer_addr_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增主键ID',
  customer_id INT UNSIGNED NOT NULL COMMENT 'customer_login表的自增ID',
  zip SMALLINT NOT NULL COMMENT '邮编',
  province SMALLINT NOT NULL COMMENT '省',
  city SMALLINT NOT NULL COMMENT '市',
  district SMALLINT NOT NULL COMMENT '区',
  address VARCHAR(200) NOT NULL COMMENT '详细地址',
  is_default TINYINT NOT NULL COMMENT '是否默认',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (customer_addr_id),
  foreign key (customer_id) references customer_login(customer_id)
) ENGINE = innodb COMMENT '用户地址表';

-- 用户登陆日志表(customer_login_log)
create table customer_login_log(
  login_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '登陆日志ID',
  customer_id INT UNSIGNED NOT NULL COMMENT '登陆用户ID',
  login_time TIMESTAMP NOT NULL COMMENT '用户登陆时间',
  login_ip INT UNSIGNED NOT NULL COMMENT '登陆IP',
  login_type TINYINT NOT NULL COMMENT '登陆类型：0未成功，1成功',
  primary key (login_id),
  foreign key (customer_id) references customer_login(customer_id)
) ENGINE = innodb COMMENT '用户登陆日志表';

-- 管理员表(amdin)
create table admin(
  admin_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '管理员ID',
  admin_name VARCHAR(20) NOT NULL COMMENT '管理员名称',
  admin_true_name VARCHAR(20) NOT NULL COMMENT '真实姓名',
  password CHAR(32) NOT NULL COMMENT 'md5加密的密码',
  admin_grade TINYINT NOT NULL DEFAULT 1 COMMENT '管理员等级', -- 从高到低依次为1,2,3,
  mobile_phone INT UNSIGNED COMMENT '手机号',
  admin_email VARCHAR(50) COMMENT '邮箱',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  register_time TIMESTAMP NOT NULL COMMENT '注册时间',
  primary key (admin_id)
);

-- 商家信息表(seller)
create table seller(
  seller_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商家ID',
  seller_name VARCHAR(20) NOT NULL COMMENT '商家名称',
  password CHAR(32) NOT NULL COMMENT 'md5加密的密码',
  seller_true_name VARCHAR(20) NOT NULL COMMENT '真实姓名',
  mobile_phone INT UNSIGNED COMMENT '手机号',
  seller_email VARCHAR(50) COMMENT '邮箱',
  seller_stats TINYINT NOT NULL DEFAULT 1 COMMENT '用户状态', -- 0为正常,1为异常
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  register_time TIMESTAMP NOT NULL COMMENT '注册时间',
  primary key (seller_id)
);

-- 品牌信息表(brand_info)
create table brand_info(
  brand_id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '品牌ID',
  brand_name VARCHAR(50) NOT NULL COMMENT '品牌名称',
  telephone VARCHAR(50) NOT NULL COMMENT '联系电话',
  brand_logo VARCHAR(100) COMMENT '品牌logo URL',
  brand_desc VARCHAR(150) COMMENT '品牌描述',
  brand_status TINYINT NOT NULL DEFAULT 0 COMMENT '品牌状态,0禁用,1启用',
  brand_order TINYINT NOT NULL DEFAULT 0 COMMENT '知名度', -- 排序时的依据
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (brand_id)
)ENGINE=innodb COMMENT '品牌信息表';


-- 分类信息表(product_category)
create table product_category(
  category_id SMALLINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '分类ID',
  category_name VARCHAR(10) NOT NULL COMMENT '分类名称',
  parent_id SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '父分类ID', 
  category_level TINYINT NOT NULL DEFAULT 1 COMMENT '分类层级', -- 从高到低一次为1 2 3 ...
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '修改时间',
  primary key (category_id)
)ENGINE=innodb COMMENT '商品分类表';


-- 商品信息表(product_info)
create table product_info(
  product_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品ID',
  product_name VARCHAR(20) NOT NULL COMMENT '商品名称',
  brand_id SMALLINT UNSIGNED  NOT NULL COMMENT '品牌表中的ID',
  category_id SMALLINT UNSIGNED NOT NULL COMMENT '分类信息表的ID',
  seller_id INT UNSIGNED NOT NULL COMMENT '商家ID',
  price DECIMAL(8,2) NOT NULL COMMENT '单价',
  publish_status TINYINT NOT NULL DEFAULT 0 COMMENT '上下架状态：0下架1上架',
  audit_status TINYINT NOT NULL DEFAULT 0 COMMENT '审核状态：0未审核，1已审核',
  production_date DATETIME NOT NULL COMMENT '生产日期',
  shelf_life INT NOT NULL COMMENT '商品有效期',
  descript TEXT NOT NULL COMMENT '商品描述', -- 描述商品的颜色、尺码、重量等各种信息
  indate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品录入时间',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (product_id),
  foreign key (brand_id) references brand_info(brand_id),
  foreign key (category_id) references product_category(category_id),
  foreign key (seller_id) references seller(seller_id)
) ENGINE = innodb COMMENT '商品信息表';

-- 商品图片表(productpicinfo)
create table product_pic_info(
  product_pic_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '商品图片ID',
  product_id INT UNSIGNED NOT NULL COMMENT '商品ID',
  pic_desc VARCHAR(50) COMMENT '图片描述',
  pic_url VARCHAR(200) NOT NULL COMMENT '图片URL',
  is_master TINYINT NOT NULL DEFAULT 0 COMMENT '是否主图：0.非主图1.主图',
  pic_order TINYINT NOT NULL DEFAULT 0 COMMENT '重要性', -- 图片排序的依据
  pic_status TINYINT NOT NULL DEFAULT 1 COMMENT '图片是否有效：0无效 1有效',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT  '修改时间',
  primary key (product_pic_id),
  foreign key (product_id) references product_info(product_id)
)ENGINE=innodb COMMENT '商品图片信息表';

-- 订单主表(order_master)
create table order_master(
  order_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  order_sn BIGINT UNSIGNED NOT NULL COMMENT '订单编号 yyyymmddnnnnnnnn',
  customer_id INT UNSIGNED NOT NULL COMMENT '用户ID',
  customer_addr_id INT UNSIGNED NOT NULL COMMENT '用户地址表中的地址ID',
  order_money DECIMAL(8,2) NOT NULL COMMENT '订单金额',
  shipping_comp_name VARCHAR(10) COMMENT '快递公司名称',
  shipping_sn VARCHAR(50) COMMENT '快递单号',
  create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  receive_time DATETIME COMMENT '收货时间',
  order_status TINYINT NOT NULL DEFAULT 0 COMMENT '订单状态',
  order_point INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单积分',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (order_id),
  foreign key (customer_id) references customer_login(customer_id),
  foreign key (customer_addr_id) references customer_addr(customer_addr_id)
)ENGINE = innodb COMMENT '订单主表';

-- 订单详情表(order_detail)
create table order_detail(
  order_detail_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单详情表ID',
  order_id INT UNSIGNED NOT NULL COMMENT '订单ID', 
  product_id INT UNSIGNED NOT NULL COMMENT '商品ID',
  product_cnt INT NOT NULL DEFAULT 1 COMMENT '购买商品数量',
  modified_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  primary key (order_detail_id),
  foreign key (order_id) references order_master(order_id),
  foreign key (product_id) references product_info(product_id)
)ENGINE = innodb COMMENT '订单详情表';

-- 商品评论表(product_comment)
create table product_comment(
  comment_id INT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '评论ID',
  product_id INT UNSIGNED NOT NULL COMMENT '商品ID',
  order_id INT UNSIGNED NOT NULL COMMENT '订单ID', 
  customer_id INT UNSIGNED NOT NULL COMMENT '用户ID',
  content VARCHAR(300) NOT NULL COMMENT '评论内容',
  audit_time TIMESTAMP NOT NULL COMMENT '评论时间',
  primary key (comment_id),
  foreign key (product_id) references product_info(product_id),
  foreign key (order_id) references order_master(order_id),
  foreign key (customer_id) references customer_login(customer_id)
) ENGINE = innodb COMMENT '商品评论表';


-- 购物车表(order_cart) 
create table order_cart(
  cart_id INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车ID',
  customer_id INT UNSIGNED NOT NULL COMMENT '用户ID',
  product_id INT UNSIGNED NOT NULL COMMENT '商品ID',
  product_amount INT NOT NULL COMMENT '商品数量', -- 每种商品的数量，不是购物车所有商品的数量，类似于淘宝购物车
  primary key (cart_id),
  foreign key (customer_id) references customer_login(customer_id),
  foreign key (product_id) references product_info(product_id)
) ENGINE = innodb COMMENT '购物车表';
/*
用户ID相同的所有记录组成用户的这个购物车，购物车ID不能标识一个购物车，表中的一个记录类似于淘宝购物车中的一种商品。
*/