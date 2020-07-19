CREATE TABLE `points` (
    `user_id` BIGINT NOT NULL PRIMARY KEY COMMENT '用户ID',
    `points` INT UNSIGNED NOT NULL COMMENT '积分',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME NOT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分信息表';
