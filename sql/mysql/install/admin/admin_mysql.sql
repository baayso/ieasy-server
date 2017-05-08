
/*==============================================================*/
/* Table: admin_user                                          */
/*==============================================================*/
drop table if exists admin_user;

create table admin_user
(
   id                   bigint not null auto_increment comment '主键',
   code                 varchar(100) comment '编码',
   username             varchar(100) comment '用户名（登录名）',
   name                 varchar(100) comment '真实姓名',
   password             varchar(100) comment '密码',
   salt                 varchar(100) comment '密码盐',
   type                 smallint comment '类型',
   phone                varchar(32) comment '手机',
   email                varchar(64) comment '邮箱',
   is_lock              boolean comment '用户是否锁定',
   is_disable           boolean comment '用户是否禁用',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '后台管理用户';

ALTER TABLE admin_user ADD UNIQUE  uk_username (username) COMMENT '用户名（登录名）唯一索引';

INSERT INTO admin_user (username, name, password, salt, is_lock, is_disable) VALUES
('admin', 'admin', 'e063567f9708e598fe8b739e67a9f83c6eeb236d', '6a6a3131ec8ffe20', FALSE, FALSE);


/*==============================================================*/
/* Table: admin_role                                          */
/*==============================================================*/
drop table if exists admin_role;

create table admin_role
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(100) comment '名称',
   code                 varchar(100) comment '编码',
   descr                varchar(255) comment '描述',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '角色';

INSERT INTO admin_role (name, descr) VALUES ('系统管理员', '拥有系统的所有权限');


/*==============================================================*/
/* Table: admin_role_user_relation                            */
/*==============================================================*/
drop table if exists admin_role_user_relation;

create table admin_role_user_relation
(
   id                   bigint not null auto_increment comment 'ID',
   user_id              bigint comment '用户ID',
   role_id              bigint comment '角色ID',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '用户和角色关联表';


/*==============================================================*/
/* Table: admin_operation                                     */
/*==============================================================*/
drop table if exists admin_operation;

create table admin_operation
(
   id                   bigint not null auto_increment comment '主键',
   name                 varchar(100) comment '名称',
   code                 varchar(100) comment '编码',
   url                  varchar(255) comment '访问URL',
   parent_id            bigint comment '父级ID',
   is_parent            boolean default false comment '是否父级',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '操作（权限）';



/*==============================================================*/
/* Table: admin_role_operation_relation                       */
/*==============================================================*/
drop table if exists admin_role_operation_relation;

create table admin_role_operation_relation
(
   id                   bigint not null auto_increment comment 'ID',
   role_id              bigint comment '角色ID',
   operation_id         bigint comment '操作ID',
   create_by            varchar(100) comment '记录创建人',
   create_time          datetime comment '记录创建时间',
   modify_by            varchar(100) comment '记录修改人',
   modify_time          datetime comment '记录修改时间',
   modify_num           integer default 0 comment '记录修改次数',
   primary key (id)
) comment '角色和操作关联表';


