create database business;
CREATE TABLE `seller` (
    `id` BIGINT NOT NULL COMMENT 'ID',
    `telephone` CHAR(11) NOT NULL COMMENT '号码-座机/电话',
    `name` varcher(32) NOT NULL COMMENT '名称',
    `address` varcher(64) NOT NULL COMMENT '地址',
    `contact` varcher(8) NOT NULL COMMENT '联系人',
    `status` TINYINT(1) UNSIGNED ZEROFILL DEFAULT 1 COMMENT '状态；0-注销，1-正常',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_user_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE UNIQUE INDEX uk_seller_telephone on seller(telephone);
