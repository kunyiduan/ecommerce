create database user;
CREATE TABLE `user` (
    `id` BIGINT NOT NULL COMMENT 'ID',
    `telephone` CHAR(11) NOT NULL UNIQUE COMMENT '电话号码',
    `email` CHAR(32) DEFAULT '' COMMENT '邮箱',
    `password` CHAR(40) DEFAULT '' COMMENT '密码',
    `nick_name` VARCHAR(32) DEFAULT '' COMMENT '昵称',
    `photo` VARCHAR(255) DEFAULT '' COMMENT '头像地址',
    `birthday` DATE DEFAULT '' COMMENT '生日',
    `status` TINYINT(1) UNSIGNED ZEROFILL DEFAULT '1' COMMENT '状态；0-注销，1-正常',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_user_id PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';
CREATE UNIQUE INDEX uk_user_telephone on user(telephone);

CREATE TABLE `user_authorization` (
    `user_id` bigint(20) NOT NULL COMMENT '用户ID',
    `open_id` char(64) NOT NULL COMMENT '第三方授权码',
    `flag` tinyint(1) NOT NULL COMMENT '登录方式标识；1-微信，2-QQ，3-AppleId',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    CONSTRAINT pk_flag_user_id PRIMARY KEY (`flag`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='授权信息表';
