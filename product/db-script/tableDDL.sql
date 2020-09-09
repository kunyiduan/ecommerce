CREATE TABLE `brand` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name` VARCHAR(16) NOT NULL COMMENT '名字',
    `telephone` CHAR(12) NOT NULL COMMENT '联系方式',
    `country` VARCHAR(16) NOT NULL COMMENT '国家',
    `province` VARCHAR(16) NOT NULL COMMENT '省份',
    `city` VARCHAR(16) NOT NULL COMMENT '城市',
    `address` VARCHAR(64) NOT NULL COMMENT '详细地址',
    `picture` VARCHAR(255) DEFAULT '' COMMENT '图片地址',
    `pic_crc` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片地址伪hash索引',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_brand_id PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='品牌信息表';
create index idx_brand_picCrc on brand(pic_crc);

CREATE TABLE `product_category` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `first_classification` VARCHAR(16) NOT NULL COMMENT '商品一级分类',
    `second_classification` VARCHAR(16) NOT NULL COMMENT '商品二级分类',
    `third_classification` VARCHAR(16) NOT NULL COMMENT '商品三级分类',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_productCategory_id PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='商品分类信息表';

CREATE TABLE `product` (
    `id` BIGINT NOT NULL COMMENT 'ID',
    `name` CHAR(10) NOT NULL COMMENT '名字',
    `price` DECIMAL(8 , 2 ) NOT NULL COMMENT '价格',
    `promotion_price` DECIMAL(8 , 2 ) DEFAULT 0 COMMENT '促销价格',
    `brand_id` INT NOT NULL COMMENT '品牌编号',
    `category_id` INT NOT NULL COMMENT '分类编号',
    `is_send_points` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '购买是否有积分',
    `quantity` INT(2) UNSIGNED NOT NULL COMMENT '可购买数量',
    `sales` INT(2) UNSIGNED DEFAULT 0 COMMENT '销量',
    `status` TINYINT(1) UNSIGNED ZEROFILL NOT NULL COMMENT '状态：0-未上架，1-预售，2-上架，3-下架',
    `picture1` VARCHAR(255) DEFAULT '' COMMENT '图片地址1',
    `pic1_crc` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '图片地址1伪hash索引',
    `picture2` VARCHAR(128) DEFAULT '' COMMENT '图片地址2',
    `picture3` VARCHAR(128) DEFAULT '' COMMENT '图片地址3',
    `picture4` VARCHAR(128) DEFAULT '' COMMENT '图片地址4',
    `picture5` VARCHAR(128) DEFAULT '' COMMENT '图片地址5',
    `picture6` VARCHAR(128) DEFAULT '' COMMENT '图片地址6',
    `description` VARCHAR(128) DEFAULT '' COMMENT '描述',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_product_id PRIMARY KEY (id)
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='商品信息表';
create index idx_product_pic1Crc on product(pic1_crc);
