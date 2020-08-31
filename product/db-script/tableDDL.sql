CREATE TABLE `product_category` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `first_classes` VARCHAR(16) NOT NULL COMMENT '商品一级分类',
    `second_classes` VARCHAR(16) NOT NULL COMMENT '商品二级分类',
    `third_classes` VARCHAR(16) NOT NULL COMMENT '商品三级分类',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '更新时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='商品分类信息表';

CREATE TABLE `product` (
   `id` BIGINT NOT NULL PRIMARY KEY COMMENT 'ID',
   `name` CHAR(10) NOT NULL COMMENT '名字',
   `price` DECIMAL(8 , 2 ) NOT NULL COMMENT '商品价格',
   `market_price` DECIMAL(8 , 2 ) NOT NULL COMMENT '市场价格',
   `category_id` INT COMMENT '分类编号',
   `is_send_points` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '购买是否有积分',
   `quantity` INT(2) UNSIGNED NOT NULL COMMENT '可购买数量',
   `sales` INT(2) UNSIGNED COMMENT '销量',
   `status` TINYINT(1) UNSIGNED ZEROFILL NOT NULL COMMENT '状态：0-未上架，1-预售，2-上架，3-下架',
   `description` VARCHAR(32) COMMENT '描述',
   `create_time` DATETIME NOT NULL COMMENT '创建时间',
   `update_time` DATETIME NOT NULL COMMENT '更新时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='商品信息表';
