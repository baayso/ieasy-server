
/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
drop table if exists user;
create table user
(
    id                   bigint not null auto_increment comment 'ID',
    username             varchar(100) comment '用户名',
    name                 varchar(100) default '' comment '姓名',
    password             varchar(100) comment '密码',
    salt                 varchar(100) default '' comment '盐',
    type                 smallint default 0 comment '类型',
    phone                varchar(32) comment '手机号码',
    email                varchar(64) comment '邮箱',
    locked               boolean default FALSE comment '是否已锁定',
    disabled             boolean default FALSE comment '是否已禁用',
    deleted              boolean default FALSE comment '是否已删除',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '用户表';

-- 唯一索引
ALTER TABLE user ADD UNIQUE uk_username (username) COMMENT '用户名唯一索引';
ALTER TABLE user ADD UNIQUE uk_phone (phone) COMMENT '手机号码唯一索引';
ALTER TABLE user ADD UNIQUE uk_email (email) COMMENT '邮箱唯一索引';



/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
drop table if exists role;
create table role
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) comment '名称',
    code                 varchar(100) comment '编码',
    descr                varchar(255) default '' comment '描述',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '角色表';

-- 唯一索引
ALTER TABLE role ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';



/*==============================================================*/
/* Table: user_and_role_ref                                     */
/*==============================================================*/
drop table if exists user_and_role_ref;
create table user_and_role_ref
(
    id                   bigint not null auto_increment comment 'ID',
    user_id              bigint comment '用户ID',
    role_id              bigint comment '角色ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '用户与角色关联表';



/*==============================================================*/
/* Table: user_group                                            */
/*==============================================================*/
drop table if exists user_group;
create table user_group
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) comment '名称',
    code                 varchar(100) comment '编码',
    descr                varchar(255) default '' comment '描述',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '用户组';

-- 唯一索引
ALTER TABLE user_group ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';



/*==============================================================*/
/* Table: user_group_and_user_ref                               */
/*==============================================================*/
drop table if exists user_group_and_user_ref;
create table user_group_and_user_ref
(
    id                   bigint not null auto_increment comment 'ID',
    user_group_id        bigint comment '用户组ID',
    user_id              bigint comment '用户ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '用户组与用户关联表';



/*==============================================================*/
/* Table: user_group_and_role_ref                               */
/*==============================================================*/
drop table if exists user_group_and_role_ref;
create table user_group_and_role_ref
(
    id                   bigint not null auto_increment comment 'ID',
    user_group_id        bigint comment '用户组ID',
    role_id              bigint comment '角色ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
)comment '用户组与角色关联表';



/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
drop table if exists permission;
create table permission
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) default '' comment '名称',
    code                 varchar(100) comment '编码',
    type                 smallint comment '类型',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '权限表';

-- 唯一索引
ALTER TABLE permission ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';



/*==============================================================*/
/* Table: role_and_permission_ref                               */
/*==============================================================*/
drop table if exists role_and_permission_ref;
create table role_and_permission_ref
(
    id                   bigint not null auto_increment comment 'ID',
    role_id              bigint comment '角色ID',
    permission_id        bigint comment '权限ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '角色与权限关联表';



/*==============================================================*/
/* Table: permission_menu                                       */
/*==============================================================*/
drop table if exists permission_menu;
create table permission_menu
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) comment '名称',
    code                 varchar(100) comment '编码',
    url                  varchar(255) comment 'URL',
    icon                 varchar(50) default '' comment '图标',
    parent_id            bigint comment '父级ID',
    is_parent            boolean default FALSE comment '是否父级',
    is_show              boolean default TRUE comment '是否显示',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '菜单表';

-- 唯一索引
ALTER TABLE permission_menu ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';


/*==============================================================*/
/* Table: permission_and_menu_ref                               */
/*==============================================================*/
drop table if exists permission_and_menu_ref;
create table permission_and_menu_ref
(
    id                   bigint not null auto_increment comment 'ID',
    permission_id        bigint comment '权限ID',
    menu_id              bigint comment '菜单ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '权限与菜单关联表';



/*==============================================================*/
/* Table: permission_element                                    */
/*==============================================================*/
drop table if exists permission_element;
create table permission_element
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) default '' comment '名称',
    code                 varchar(100) comment '编码',
    type                 smallint comment '类型',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '页面元素表';

-- 唯一索引
ALTER TABLE permission_element ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';


/*==============================================================*/
/* Table: permission_and_element_ref                            */
/*==============================================================*/
drop table if exists permission_and_element_ref;
create table permission_and_element_ref
(
    id                   bigint not null auto_increment comment 'ID',
    permission_id        bigint comment '权限ID',
    element_id           bigint comment '页面元素ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '权限与页面元素关联表';



/*==============================================================*/
/* Table: permission_file                                       */
/*==============================================================*/
drop table if exists permission_file;
create table permission_file
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) comment '名称',
    code                 varchar(100) comment '编码',
    type                 smallint comment '类型',
    path                 varchar(255) comment '路径',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '文件表';

-- 唯一索引
ALTER TABLE permission_file ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';



/*==============================================================*/
/* Table: permission_and_file_ref                               */
/*==============================================================*/
drop table if exists permission_and_file_ref;
create table permission_and_file_ref
(
    id                   bigint not null auto_increment comment 'ID',
    permission_id        bigint comment '权限ID',
    file_id              bigint comment '文件ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '权限与文件关联表';



/*==============================================================*/
/* Table: permission_operation                                  */
/*==============================================================*/
drop table if exists permission_operation;
create table permission_operation
(
    id                   bigint not null auto_increment comment 'ID',
    name                 varchar(100) comment '名称',
    code                 varchar(100) comment '编码',
    type                 smallint comment '类型',
    url_prefix           varchar(255) comment '拦截URL前缀',
    is_parent            boolean default FALSE comment '是否父级',
    parent_id            bigint comment '父级ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '功能操作表';

-- 唯一索引
ALTER TABLE permission_operation ADD UNIQUE uk_code (code) COMMENT '编码唯一索引';



/*==============================================================*/
/* Table: permission_and_operation_ref                          */
/*==============================================================*/
drop table if exists permission_and_operation_ref;
create table permission_and_operation_ref
(
    id                   bigint not null auto_increment comment 'ID',
    permission_id        bigint comment '权限ID',
    operation_id         bigint comment '功能操作ID',
    create_by            varchar(100) comment '记录创建人',
    create_time          datetime comment '记录创建时间',
    modify_by            varchar(100) comment '记录修改人',
    modify_time          datetime comment '记录修改时间',
    primary key (id)
) comment '权限与功能操作关联表';


