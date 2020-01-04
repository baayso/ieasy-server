# iEasy Server  
> Spring Boot 脚手架项目  
> 项目原地址: https://github.com/baayso/spring-boot-demo
> 
> 请注意：druid升级到1.1.20版本，实体类中有LocalDateTime类型字段时报SQLFeatureNotSupportedException的问题依旧没有解决(相关issues: https://github.com/alibaba/druid/issues/3302 )。目前解决办法：1.换数据库连接池，推荐HikariCP；2.使用低版本mybatis和mybatis-plus


[![Jdk Version](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build Status](https://travis-ci.org/baayso/ieasy-server.svg?branch=master)](https://travis-ci.org/baayso/ieasy-server)

## 环境要求：
* Jdk 8.0+
* Maven 3.2+
* MySQL 5.5+

## 框架：
* 后端
  * [MyBatis](http://www.mybatis.org/mybatis-3)
  * [MyBatis-Plus](https://gitee.com/baomidou/mybatis-plus)
  * [EasyOpen](https://gitee.com/durcframework/easyopen)
  * [vjkit](https://github.com/vipshop/vjtools/tree/master/vjkit)
  * [Lombok](https://www.projectlombok.org)
  * [JFinal Enjoy](https://www.jfinal.com/doc/6-1)
  * [其他请查看pom.xml](https://github.com/baayso/ieasy-server/blob/master/pom.xml)
* 前端
  * [LayUI](https://www.layui.com)

## 快速开始：
0. 准备依赖
   * git clone https://github.com/baayso/commons
   * **`build.bat`** (Windows) Or **`./build.sh`** (Linux/Mac OS)
1. git clone https://github.com/baayso/ieasy-server.git
2. 安装 MySQL 5.5+
3. 执行 [SQL 脚本](https://github.com/baayso/ieasy-server/tree/master/sql/mysql/install)（注：默认创建的数据库名称为 **ieasy_server**，可以在[这个SQL文件里](https://github.com/baayso/ieasy-server/blob/master/sql/mysql/install/common/common_mysql.sql)进行修改）
   * Windows  
     **`sql\mysql\install\install.bat`**  
     > 注：根据提示输入数据库的 ip、port、username
   * Linux/Mac OS  
     **`./sql/mysql/install/install.sh 127.0.0.1 3306 root`**  
     > 注：在命令后输入数据库的 ip、port、username
4. 修改项目配置文件里数据库的 ip、port、username、password
   > 注：各环境配置文件[请参见](#config)
5. **`build.bat`** (Windows) Or **`./build.sh`** (Linux/Mac OS)
6. 执行[启动脚本](https://github.com/baayso/ieasy-server/tree/master/bin)
   * 开发环境  
     **`bin\start.bat 8888 dev`** (Windows) Or **`./bin/start.sh 8888 dev`** (Linux/Mac OS)  
     > 注：端口号为 8888
   * 测试环境  
     **`bin\start.bat 8888 test`** (Windows) Or **`./bin/start.sh 8888 test`** (Linux/Mac OS)  
     > 注：端口号为 8888
   * 生产环境  
     **`bin\start.bat 8888 pro`** (Windows) Or **`./bin/start.sh 8888 pro`** (Linux/Mac OS)  
     > 注：端口号为 8888

## [配置文件：](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config)
* <span id = "config">多环境配置文件</span>
  * [基础配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application.yml)（注：[默认加载开发环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application.yml#L28)）
  * [开发环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-dev.yml)
  * [测试环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-test.yml)
  * [生产环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-pro.yml)
* [MyBatis 配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/mybatis-config.xml)

## [多租户 SQL 解析器：](https://mybatis.plus/guide/tenant.html)
> [配置多租户 SQL 解析器](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/mybatis/MybatisPlusConfig.java#L70)

## 访问：
* http://localhost:8888/welcome
* http://localhost:8888/welcome/index (LayUI后台布局)
* http://localhost:8888/welcome/index2 (LayUI后台布局2)
* http://localhost:8888/demo/api
* http://localhost:8888/demo/api/page?pageSize=100&pageNum=1
* http://localhost:8888/demo/api/deletes?id=1
* http://localhost:8888/demo/api/create
* http://localhost:8888/demo/api/creates
* http://localhost:8888/demo/api/update?id=7
* http://localhost:8888/demo/api/hello
* http://localhost:8888/api/doc (EasyOpen API文档页面)

## Dependencies:
* https://github.com/baayso/commons
