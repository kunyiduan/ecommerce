CREATE TABLE `user` (
    `id` BIGINT NOT NULL PRIMARY KEY COMMENT 'ID',
    `telephone` CHAR(11) NOT NULL UNIQUE COMMENT '电话号码',
    `email` CHAR(32) DEFAULT NULL COMMENT '邮箱',
    `password` CHAR(40) DEFAULT NULL COMMENT '密码',
    `nick_name` VARCHAR(32) DEFAULT NULL COMMENT '昵称',
    `photo` VARCHAR(255) DEFAULT NULL COMMENT '头像地址',
    `birthday` DATE DEFAULT NULL COMMENT '生日',
    `status` TINYINT(1) UNSIGNED ZEROFILL DEFAULT '1' COMMENT '状态；0-注销，1-正常',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

CREATE TABLE `user_authorization` (
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `open_id` char(64) NOT NULL COMMENT '第三方授权码',
    `flag` tinyint(1) NOT NULL COMMENT '登录方式标识；1-微信，2-QQ，3-AppleId',
    `create_ime` datetime NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`flag`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权信息表';
