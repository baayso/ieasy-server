set names utf8mb4;

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- SET time_zone = "+00:00";

SET FOREIGN_KEY_CHECKS = 0; -- 取消外键约束

source common/common-mysql.sql;

source system/sys_area-mysql.sql;
source system/sys_tenant-mysql.sql;
-- source system/sys_user-mysql.sql;

source demo/demo_user-mysql.sql;
