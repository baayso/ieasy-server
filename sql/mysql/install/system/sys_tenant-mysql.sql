
USE ieasy_server;

-- ----------------------------
-- Table structure for sys_tenant
-- ----------------------------
DROP TABLE IF EXISTS sys_tenant;
CREATE TABLE sys_tenant (
    id              BIGINT        UNSIGNED    NOT NULL    AUTO_INCREMENT     COMMENT '主键',
    code            VARCHAR(50)               NOT NULL                       COMMENT '租户编码',
    name            VARCHAR(255)              NOT NULL                       COMMENT '租户名称',
    type            SMALLINT      UNSIGNED                DEFAULT 128        COMMENT '租户类型',
    -- state           SMALLINT      UNSIGNED                DEFAULT 128        COMMENT '租户状态',
    remark          VARCHAR(3000)                         DEFAULT NULL       COMMENT '备注',
    disabled        BOOLEAN                               DEFAULT FALSE      COMMENT '是否已禁用',
    deleted         BOOLEAN                               DEFAULT FALSE      COMMENT '是否删除',
    create_by       VARCHAR(100)                                             COMMENT '记录创建人',
    create_time     DATETIME                                                 COMMENT '记录创建时间',
    update_by       VARCHAR(100)                                             COMMENT '记录修改人',
    update_time     DATETIME                                                 COMMENT '记录修改时间',
    PRIMARY KEY (id)
) COMMENT '租户';

-- 唯一索引
ALTER TABLE sys_tenant ADD UNIQUE uk_code (code) COMMENT '租户编码唯一索引';

-- ----------------------------
-- Records of sys_tenant
-- ----------------------------
INSERT INTO sys_tenant VALUES
(1, 'ieasy_tenant_1', '测试租户', 128, NULL, FALSE, FALSE, 'init', NOW(), 'init', NOW());

