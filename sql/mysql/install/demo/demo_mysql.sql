
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
    `deleted`       BOOLEAN                DEFAULT FALSE,
    create_by       VARCHAR(100)                              COMMENT '记录创建人',
    create_time     DATETIME                                  COMMENT '记录创建时间',
    modify_by       VARCHAR(100)                              COMMENT '记录修改人',
    modify_time     DATETIME                                  COMMENT '记录修改时间',
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, 1, 'name1', 20, 128, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (2, 2, 'name2', 20, 256, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (3, 2, 'name3', 20, 384, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (4, 1, 'name4', 20, 512, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (5, 1, 'name5', 21, 640, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (6, 1, 'name6', 21, 768, '', 0, TRUE,  'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (7, 1, 'name6', 21, 128, '', 2, FALSE, 'init', NOW(), 'init', NOW());
