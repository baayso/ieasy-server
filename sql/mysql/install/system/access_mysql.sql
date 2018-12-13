
/*==============================================================*/
/* 表：接入方                                                    */
/*==============================================================*/
drop table if exists access;

create table access
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(100) comment '名称',
   code                 varchar(100) comment '编码',
   type                 varchar(100) comment '类型',
   access_key           varchar(100) comment 'AccessKey',
   access_secret        varchar(100) comment 'AccessSecret',
   salt                 varchar(100) comment '数据盐',
   is_enable            boolean default FALSE comment '是否启用',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment '接入方';

-- 唯一索引
ALTER TABLE access ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';
ALTER TABLE access ADD UNIQUE uk_access_key (access_key) COMMENT 'AccessKey唯一索引';


/*==============================================================*/
/* 表: 接入组                                                    */
/*==============================================================*/
drop table if exists access_group;

create table access_group
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(100) comment '名称',
   code                 varchar(100) comment '编码',
   descr                varchar(255) comment '描述',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment '接入组';

INSERT INTO access_group (name, descr) VALUES ('测试', '拥有所有API访问权限');


/*==============================================================*/
/* 表: 接入方与接入组关联表                                        */
/*==============================================================*/
drop table if exists access_and_access_group_ref;

create table access_and_access_group_ref
(
   id                   bigint not null auto_increment comment 'ID',
   access_id            bigint comment '接入方ID',
   group_id             bigint comment '接入组ID',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment '接入方和接入组关联表';


/*==============================================================*/
/* 表：API                                                      */
/*==============================================================*/
drop table if exists access_api;

create table access_api
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(100) comment '名称',
   code                 varchar(100) comment '编码',
   url                  varchar(255) comment 'API访问地址',
   descr                varchar(255) comment '描述',
   parent_id            bigint comment '父级ID',
   is_parent            boolean default false comment '是否父级',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment 'API';


/*==============================================================*/
/* 表：接入方与API关联表                                           */
/*==============================================================*/
drop table if exists access_group_and_api_ref;

create table access_group_and_api_ref
(
   id                   bigint not null auto_increment comment 'ID',
   group_id             bigint comment '接入组ID',
   api_id               bigint comment 'API_ID',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment '接入组和API关联表';


/*==============================================================*/
/* 表：接入方IP                                                  */
/*==============================================================*/
drop table if exists access_ip;

create table access_ip
(
   id                   bigint not null auto_increment comment 'ID',
   access_id            bigint comment '接入方ID',
   type                 varchar(100) comment '类型',
   address              varchar(64) comment 'IP地址',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   primary key (id)
) comment '接入方IP';

