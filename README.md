# Spring Boot Demo  
> Spring Boot 脚手架项目

[![Jdk Version](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build Status](https://travis-ci.org/baayso/spring-boot-demo.svg?branch=master)](https://travis-ci.org/baayso/spring-boot-demo)

## 环境要求：
* Jdk 8.0+
* Maven 3.2+
* MySQL 5.5+

## 框架：
* [MyBatis](http://www.mybatis.org/mybatis-3)
* [MyBatis-Plus](https://gitee.com/baomidou/mybatis-plus)
* [EasyOpen](https://gitee.com/durcframework/easyopen)
* [vjkit](https://github.com/vipshop/vjtools/tree/master/vjkit)
* [Lombok](https://www.projectlombok.org)
* [其他请看pom.xml](https://github.com/baayso/spring-boot-demo/blob/master/pom.xml)

## 快速开始：
1. git clone https://github.com/baayso/spring-boot-demo.git
2. 安装 MySQL 5.5+
3. 执行 [SQL 脚本](https://github.com/baayso/spring-boot-demo/tree/master/sql/mysql/install)
   * Windows  
     **`sql\install.bat`**  
     > 注意：根据提示输入数据库的 ip、port、username
   * Linux/Mac OS  
     **`./sql/install.sh 127.0.0.1 3306 root`**  
     > 注意：在命令后输入数据库的 ip、port、username
4. **`build.bat`** (Windows) Or **`./build.sh`** (Linux/Mac OS)
5. 执行[启动脚本](https://github.com/baayso/spring-boot-demo/tree/master/bin)
   * 开发环境（端口号：8888）  
     **`bin\start.bat 8888 dev`** (Windows) Or **`./bin/start.sh 8888 dev`** (Linux/Mac OS)
   * 测试环境（端口号：8888）  
     **`bin\start.bat 8888 test`** (Windows) Or **`./bin/start.sh 8888 test`** (Linux/Mac OS)
   * 生产环境（端口号：8888）  
     **`bin\start.bat 8888 pro`** (Windows) Or **`./bin/start.sh 8888 pro`** (Linux/Mac OS)

## 配置文件
* 多环境配置文件
  * [基础配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/application.yml)（[默认加载开发环境配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/application.yml#L28)）
  * [开发环境配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/application-dev.yml)
  * [测试环境配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/application-test.yml)
  * [生产环境配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/application-pro.yml)
* [MyBatis 配置文件](https://github.com/baayso/spring-boot-demo/blob/master/src/main/resources/config/mybatis-config.xml)

## [多租户 SQL 解析器：](https://mybatis.plus/guide/tenant.html)
> [配置多租户 SQL 解析器](https://github.com/baayso/spring-boot-demo/blob/master/src/main/java/com/baayso/springboot/config/mybatis/MybatisPlusConfig.java#L70)

## 访问：
* http://localhost:8888/welcome
* http://localhost:8888/demo/api
* http://localhost:8888/demo/api/page?pageSize=100&pageNum=1
* http://localhost:8888/demo/api/deletes?id=1
* http://localhost:8888/demo/api/create
* http://localhost:8888/demo/api/creates
* http://localhost:8888/demo/api/update?id=7
* http://localhost:8888/demo/api/hello
* http://localhost:8888/api/doc

## Dependencies:
* https://github.com/baayso/commons
