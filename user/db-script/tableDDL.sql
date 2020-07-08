CREATE TABLE `iot_user_oven` (
    `id` bigint NOT NULL PRIMARY KEY COMMENT 'ID',
    `telephone` CHAR(11) NOT NULL UNIQUE COMMENT '电话号码',
    `fpassword` CHAR(32) DEFAULT NULL COMMENT '密码；第三方登录可能没有传密码',
    `nickName` VARCHAR(32) NOT NULL COMMENT '昵称',
    `photo` varchar(255) default null comment '头像',
    `fstatus` TINYINT(1) DEFAULT '1' COMMENT '状态；0-注销，1-正常',
    `createTime` DATETIME NOT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='烤箱用户表';

CREATE TABLE `iot_user_oven_authorization` (
    `userId` bigint(20) NOT NULL COMMENT '用户ID',
    `openid` char(64) NOT NULL COMMENT '第三方授权码',
    `flag` tinyint(1) NOT NULL COMMENT '登录方式标识；1-微信，2-QQ，3-AppleId',
    `createTime` datetime NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`flag`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='烤箱用户第三方授权登录表';
