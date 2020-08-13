set names utf8;

-- SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
-- SET time_zone = "+00:00";

SET FOREIGN_KEY_CHECKS = 0; -- 取消外键约束

source common/common_mysql.sql;
source common/area_mysql.sql;

source system/user_mysql.sql;
source system/sys_tenant-mysql.sql;

source demo/demo_mysql.sql;

source dataway/dataway.sql;
