
USE ieasy_server;

CREATE TABLE `interface_info` (
    `api_id`          int(11)      NOT NULL AUTO_INCREMENT   COMMENT 'ID',
    `api_method`      varchar(12)  NOT NULL                  COMMENT 'HttpMethod：GET、PUT、POST',
    `api_path`        varchar(512) NOT NULL                  COMMENT '拦截路径',
    `api_status`      int(2)       NOT NULL                  COMMENT '状态：0草稿，1发布，2有变更，3禁用',
    `api_comment`     varchar(255)     NULL                  COMMENT '注释',
    `api_type`        varchar(24)  NOT NULL                  COMMENT '脚本类型：SQL、DataQL',
    `api_script`      mediumtext   NOT NULL                  COMMENT '查询脚本：xxxxxxx',
    `api_schema`      mediumtext       NULL                  COMMENT '接口的请求/响应数据结构',
    `api_sample`      mediumtext       NULL                  COMMENT '请求/响应/请求头样本数据',
    `api_option`      mediumtext       NULL                  COMMENT '扩展配置信息',
    `api_create_time` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `api_gmt_time`    datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='Dataway 中的API';

INSERT INTO `interface_info` VALUES (1, 'GET', '/app/api/dataway/test', 1, 'Test API', 'DataQL', 'var queryUser = @@sql()<%\n    SELECT * FROM ieasy_tenant_1.demo_user WHERE id = 1 OR id = 2\n%>;\n\nreturn queryUser();\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '{\"resultStructure\":true,\"responseFormat\":\"{\\n  \\\"success\\\"      : \\\"@resultStatus\\\",\\n  \\\"message\\\"      : \\\"@resultMessage\\\",\\n  \\\"code\\\"         : \\\"@resultCode\\\",\\n  \\\"lifeCycleTime\\\": \\\"@timeLifeCycle\\\",\\n  \\\"executionTime\\\": \\\"@timeExecution\\\",\\n  \\\"value\\\"        : \\\"@resultData\\\"\\n}\"}', '2020-05-16 22:52:48', '2020-05-16 23:23:28');


CREATE TABLE `interface_release` (
    `pub_id`          int(11)      NOT NULL AUTO_INCREMENT   COMMENT 'Publish ID',
    `pub_api_id`      int(11)      NOT NULL                  COMMENT '所属API ID',
    `pub_method`      varchar(12)  NOT NULL                  COMMENT 'HttpMethod：GET、PUT、POST',
    `pub_path`        varchar(512) NOT NULL                  COMMENT '拦截路径',
    `pub_status`      int(2)       NOT NULL                  COMMENT '状态：0有效，1无效（可能被下线）',
    `pub_type`        varchar(24)  NOT NULL                  COMMENT '脚本类型：SQL、DataQL',
    `pub_script`      mediumtext   NOT NULL                  COMMENT '查询脚本：xxxxxxx',
    `pub_script_ori`  mediumtext   NOT NULL                  COMMENT '原始查询脚本，仅当类型为SQL时不同',
    `pub_schema`      mediumtext       NULL                  COMMENT '接口的请求/响应数据结构',
    `pub_sample`      mediumtext       NULL                  COMMENT '请求/响应/请求头样本数据',
    `pub_option`      mediumtext       NULL                  COMMENT '扩展配置信息',
    `pub_release_time`datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间（下线不更新）',
    PRIMARY KEY (`pub_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COMMENT='Dataway API 发布历史';

create index idx_interface_release on interface_release (pub_api_id);

INSERT INTO `interface_release` VALUES (1, 1, 'GET', '/app/api/dataway/test', 0, 'DataQL', 'var queryUser = @@sql()<%\n    SELECT * FROM demo_user\n%>;\n\nreturn queryUser();\n', 'var queryUser = @@sql()<%\n    SELECT * FROM demo_user\n%>;\n\nreturn queryUser();\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '{\"resultStructure\":true,\"responseFormat\":\"{\\n  \\\"success\\\"      : \\\"@resultStatus\\\",\\n  \\\"message\\\"      : \\\"@resultMessage\\\",\\n  \\\"code\\\"         : \\\"@resultCode\\\",\\n  \\\"lifeCycleTime\\\": \\\"@timeLifeCycle\\\",\\n  \\\"executionTime\\\": \\\"@timeExecution\\\",\\n  \\\"value\\\"        : \\\"@resultData\\\"\\n}\"}', '2020-05-16 22:53:24');
INSERT INTO `interface_release` VALUES (2, 1, 'GET', '/app/api/dataway/test', 0, 'DataQL', 'var queryUser = @@sql()<%\n    SELECT * FROM demo_user WHERE id = 1\n%>;\n\nreturn queryUser();\n', 'var queryUser = @@sql()<%\n    SELECT * FROM demo_user WHERE id = 1\n%>;\n\nreturn queryUser();\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '{\"resultStructure\":true,\"responseFormat\":\"{\\n  \\\"success\\\"      : \\\"@resultStatus\\\",\\n  \\\"message\\\"      : \\\"@resultMessage\\\",\\n  \\\"code\\\"         : \\\"@resultCode\\\",\\n  \\\"lifeCycleTime\\\": \\\"@timeLifeCycle\\\",\\n  \\\"executionTime\\\": \\\"@timeExecution\\\",\\n  \\\"value\\\"        : \\\"@resultData\\\"\\n}\"}', '2020-05-16 23:22:36');
INSERT INTO `interface_release` VALUES (3, 1, 'GET', '/app/api/dataway/test', 0, 'DataQL', 'var queryUser = @@sql()<%\n    SELECT * FROM ieasy_tenant_1.demo_user WHERE id = 1 OR id = 2\n%>;\n\nreturn queryUser();\n', 'var queryUser = @@sql()<%\n    SELECT * FROM demo_user WHERE id = 1 OR id = 2\n%>;\n\nreturn queryUser();\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '{\"resultStructure\":true,\"responseFormat\":\"{\\n  \\\"success\\\"      : \\\"@resultStatus\\\",\\n  \\\"message\\\"      : \\\"@resultMessage\\\",\\n  \\\"code\\\"         : \\\"@resultCode\\\",\\n  \\\"lifeCycleTime\\\": \\\"@timeLifeCycle\\\",\\n  \\\"executionTime\\\": \\\"@timeExecution\\\",\\n  \\\"value\\\"        : \\\"@resultData\\\"\\n}\"}', '2020-05-16 23:23:28');
