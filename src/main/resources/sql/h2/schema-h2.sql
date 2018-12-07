-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
    `id`            BIGINT(20) NOT NULL    AUTO_INCREMENT,
    `tenant_id`     BIGINT(20) NOT NULL                       COMMENT '租户ID',
    `name`          VARCHAR(255)           DEFAULT NULL,
    `age`           INT(11)    NULL        DEFAULT NULL,
    `status`        SMALLINT   UNSIGNED    DEFAULT 128,
    `intro`         TEXT                   DEFAULT NULL,
    `version`       INTEGER                DEFAULT 0,
    `is_deleted`    BOOLEAN                DEFAULT FALSE,
    create_by       VARCHAR(100)                              COMMENT '记录创建人',
    create_time     DATETIME                                  COMMENT '记录创建时间',
    modify_by       VARCHAR(100)                              COMMENT '记录修改人',
    modify_time     DATETIME                                  COMMENT '记录修改时间',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARSET = utf8;
