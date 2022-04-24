# 留言表建表语句
CREATE TABLE `msg_interaction_message`
(
    `id`                  BIGINT(20)   NOT NULL COMMENT '主键，留言ID',
    `internet_name`       VARCHAR(16)  NOT NULL COMMENT '留言者网名',
    `email_address`       VARCHAR(128) NOT NULL COMMENT '邮箱',
    `postal_code`         INT(6)                DEFAULT NULL COMMENT '邮政编码',
    `residential_address` VARCHAR(255)          DEFAULT NULL COMMENT '居住地址',
    `message_type`        INT(4)       NOT NULL COMMENT '留言类型，1:咨询、2:建议、3:投诉、4:分享',
    `message_theme`       VARCHAR(128) NOT NULL COMMENT '留言主题',
    `message_content`     TEXT         NOT NULL COMMENT '留言内容',
    `message_pic`         VARCHAR(64)           DEFAULT NULL COMMENT '留言图片',
    `message_attachment`  VARCHAR(64)           DEFAULT NULL COMMENT '留言附件',
    `phone_num`           VARCHAR(11)  NOT NULL COMMENT '手机号',
    `create_date`         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '留言创建时间',
    `modifier_id`         BIGINT(20)            DEFAULT NULL COMMENT '处理人ID',
    `modify_date`         TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后处理时间',
    `check_status`        INT(4)       NOT NULL DEFAULT '1' COMMENT '审核状态，1:通过、0:不通过',
    `reply_status`        INT(4)       NOT NULL DEFAULT '0' COMMENT '受理状态，1:已受理、0:未受理',
    `del_flag`            INT(4)                DEFAULT '0' COMMENT '删除标记 0:正常;1:删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='网民留言表';

# 留言回复表建表语句
CREATE TABLE `msg_message_reply`
(
    `id`               BIGINT(20) NOT NULL COMMENT '主键，留言回复ID',
    `message_id`       BIGINT(20) NOT NULL COMMENT '留言ID',
    `reply_type`       INT(4)     NOT NULL DEFAULT '1' COMMENT '回复类型，1:留言的回复、0:回复的回复',
    `reply_content`    TEXT       NOT NULL COMMENT '回复内容',
    `reply_time`       TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
    `reply_pic`        VARCHAR(64)         DEFAULT NULL COMMENT '回复图片',
    `reply_attachment` VARCHAR(64)         DEFAULT NULL COMMENT '回复附件',
    `reply_user_id`    BIGINT(20)          DEFAULT NULL COMMENT '回复人id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='网民留言回复表';

# 自动回复表
CREATE TABLE `msg_auto_reply`
(
    `id`                 BIGINT(20)   NOT NULL COMMENT '主键，自动回复ID',
    `auto_reply_theme`   VARCHAR(128) NOT NULL COMMENT '自动回复主题',
    `auto_reply_content` TEXT         NOT NULL COMMENT '自动回复内容',
    `create_date`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '自动回复创建时间',
    `modify_date`        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后处理时间',
    `del_flag`           INT(4)                DEFAULT '0' COMMENT '删除标记 0:正常;1:删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB
  DEFAULT CHARSET = utf8
  ROW_FORMAT = COMPACT COMMENT ='自动回复信息表';

# 常见问题信息表
CREATE TABLE `msg_common_qa` (
                                 `id` BIGINT (20) NOT NULL COMMENT '主键，常见问题ID',
                                 `idx` DECIMAL (11, 2) DEFAULT NULL COMMENT '序号，同一级的显示排序',
                                 `parentId` BIGINT (20) DEFAULT '0' COMMENT '父节点',
                                 `common_qa_theme` VARCHAR (128) NOT NULL COMMENT '自动回复主题',
                                 `common_qa_content` TEXT NOT NULL COMMENT '自动回复内容',
                                 `create_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '自动回复创建时间',
                                 `create_user_id` BIGINT (20) NOT NULL COMMENT '创建者id',
                                 `create_user_name` VARCHAR (20) NOT NULL COMMENT '创建者用户名',
                                 `modify_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更改时间',
                                 `del_flag` INT (4) DEFAULT '0' COMMENT '删除标记 0:正常;1:删除',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8 ROW_FORMAT = COMPACT COMMENT = '常见问题信息表';

