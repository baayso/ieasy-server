
/*==============================================================*/
/* 表：接入方                                                    */
/*==============================================================*/
drop table if exists system_access;

create table system_access
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
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '接入方';

-- 唯一索引
ALTER TABLE system_access ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';
ALTER TABLE system_access ADD UNIQUE uk_access_key (access_key) COMMENT 'AccessKey唯一索引';


/*==============================================================*/
/* 表：接入方_API中间表                                           */
/*==============================================================*/
drop table if exists system_access_api;

create table system_access_api
(
   id                   bigint not null auto_increment comment 'ID',
   access_id            bigint comment '接入方ID',
   api_id               bigint comment 'API_ID',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '接入方_API中间表';


/*==============================================================*/
/* 表：接入方IP                                                  */
/*==============================================================*/
drop table if exists system_access_ip;

create table system_access_ip
(
   id                   bigint not null auto_increment comment 'ID',
   access_id            bigint comment '接入方ID',
   type                 varchar(100) comment '类型',
   address              varchar(64) comment 'IP地址',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '接入方IP';


/*==============================================================*/
/* 表：API                                                      */
/*==============================================================*/
drop table if exists system_api;

create table system_api
(
   id                   bigint not null auto_increment comment 'ID',
   name                 varchar(100) comment '名称',
   api_path             varchar(255) comment 'API访问地址',
   descr                varchar(255) comment '描述',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment 'API';

