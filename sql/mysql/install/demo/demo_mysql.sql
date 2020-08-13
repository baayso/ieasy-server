
CREATE DATABASE IF NOT EXISTS ieasy_tenant_1
    DEFAULT CHARSET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE ieasy_tenant_1;

-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
CREATE TABLE `demo_user` (
    `id`            BIGINT        NOT NULL    AUTO_INCREMENT     COMMENT '主键',
    `tenant_code`   VARCHAR(50)   NOT NULL    DEFAULT '123'      COMMENT '租户编码',
    `name`          VARCHAR(255)              DEFAULT NULL       COMMENT '姓名',
    `age`           SMALLINT      UNSIGNED    DEFAULT 0          COMMENT '年龄',
    `status`        SMALLINT      UNSIGNED    DEFAULT 128        COMMENT '状态',
    `intro`         TEXT                      DEFAULT NULL       COMMENT '简介',
    `version`       INT                       DEFAULT 0          COMMENT '乐观锁',
    `deleted`       BOOLEAN                   DEFAULT FALSE      COMMENT '是否删除',
    create_by       VARCHAR(100)                                 COMMENT '记录创建人',
    create_time     DATETIME                                     COMMENT '记录创建时间',
    update_by       VARCHAR(100)                                 COMMENT '记录修改人',
    update_time     DATETIME                                     COMMENT '记录修改时间',
    PRIMARY KEY (`id`)
)
    COMMENT '测试'
    ENGINE = InnoDB
    AUTO_INCREMENT = 8
    DEFAULT CHARSET = utf8mb4;

-- ----------------------------
-- Records of demo_user
-- ----------------------------
INSERT INTO `demo_user` VALUES (1, '123', 'name1', 20, 128, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (2, '223', 'name2', 20, 256, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (3, '223', 'name3', 20, 384, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (4, '123', 'name4', 20, 512, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (5, '123', 'name5', 21, 640, '', 0, FALSE, 'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (6, '123', 'name6', 21, 768, '', 0, TRUE,  'init', NOW(), 'init', NOW());
INSERT INTO `demo_user` VALUES (7, '123', 'name6', 21, 128, '', 2, FALSE, 'init', NOW(), 'init', NOW());
