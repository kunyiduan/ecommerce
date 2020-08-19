CREATE TABLE `message_blacklist` (
    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `telephone` CHAR(11) NOT NULL COMMENT '电话号码',
    `mac_address` CHAR(17) NOT NULL COMMENT 'mac地址,eg:3C-58-C2-1A-1D-69',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '更新时间'
)  ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='短信黑名单表';