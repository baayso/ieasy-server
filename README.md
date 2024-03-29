# iEasy Server  
> Spring Boot 脚手架项目  
> 项目原地址: https://github.com/baayso/spring-boot-demo  
> **代码生成器：https://gitee.com/baayso/ieasy-generator**  


[![Jdk Version](https://img.shields.io/badge/JDK-17+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build Status](https://travis-ci.org/baayso/ieasy-server.svg?branch=master)](https://travis-ci.org/baayso/ieasy-server)

## 环境要求：
* Jdk 17+
* Maven 3.6+
* MySQL 5.7+

## 框架：
* 后端
  * [MyBatis](http://www.mybatis.org/mybatis-3)
  * [MyBatis-Plus](https://gitee.com/baomidou/mybatis-plus)
  * [vjkit](https://github.com/vipshop/vjtools/tree/master/vjkit)
  * [Lombok](https://www.projectlombok.org)
  * [JFinal Enjoy](https://www.jfinal.com/doc/6-1)
  * [其他请查看pom.xml](https://github.com/baayso/ieasy-server/blob/master/pom.xml)
* 前端
  * [LayUI](https://www.layui.com)

## 快速开始：
0. 准备依赖
   * git clone https://github.com/baayso/commons
   * **`build.bat`** (Windows) or **`./build.sh`** (Linux/Mac OS)
1. git clone https://github.com/baayso/ieasy-server.git
2. 安装 MySQL 5.7+
3. 执行 [SQL 脚本](https://github.com/baayso/ieasy-server/tree/master/sql/mysql/install)
   * Windows  
     **`sql\mysql\install\install.bat`**  
     > 注：根据提示输入数据库的 ip、port、username
   * Linux/Mac OS  
     **`./sql/mysql/install/install.sh 127.0.0.1 3306 root`**  
     > 注：在命令后输入数据库的 ip、port、username
4. 修改项目配置文件里数据库的 ip、port、username、password
   > 注：各环境配置文件[请参见](#config)
5. **`build.bat`** (Windows) or **`./build.sh`** (Linux/Mac OS)
6. 执行[启动脚本](https://github.com/baayso/ieasy-server/tree/master/bin)
   * 开发环境  
     **`bin\start.bat 8888 dev`** (Windows) or **`./bin/start.sh 8888 dev`** (Linux/Mac OS)  
     > 注：端口号为 8888
   * 测试环境  
     **`bin\start.bat 8888 test`** (Windows) or **`./bin/start.sh 8888 test`** (Linux/Mac OS)  
     > 注：端口号为 8888
   * 生产环境  
     **`bin\start.bat 8888 pro`** (Windows) or **`./bin/start.sh 8888 pro`** (Linux/Mac OS)  
     > 注：端口号为 8888
7. [测试](#test)

## [配置文件：](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config)
* <span id = "config">多环境配置文件</span>
  * [基础配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application.yml)（注：[默认加载开发环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application.yml#L28)）
  * [开发环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-dev.yml)
  * [测试环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-test.yml)
  * [生产环境配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/application-pro.yml)
* [MyBatis 配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/mybatis-config.xml)
* [Log4j2 配置文件](https://github.com/baayso/ieasy-server/blob/master/src/main/resources/config/log4j2.xml)
* [配置类](https://github.com/baayso/ieasy-server/tree/master/src/main/java/com/baayso/springboot/config)

## [多租户 SQL 解析器：](https://mybatis.plus/guide/tenant.html)
> [配置多租户 SQL 解析器](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/mybatis/MybatisPlusConfig.java#L44)
* [验证租户参数拦截器](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/common/interceptor/TenantInterceptor.java#L28)
* [配置验证租户参数拦截器](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/web/MvcConfig.java#L38)
* [MyBatis-Plus租户处理器(schema 级)](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/mybatis/BasicTenantSchemaHandler.java#L21): 执行SQL前自动在表名前增加schema，如: `demo_user -> ieasy_tenant_1.demo_user`
* 注意：[租户SQL解析器(schema 级)](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/mybatis/CustomTenantSchemaSqlParser.java#L29) 未支持在WHERE条件中使用子查询，即在WHERE条件中使用子查询时不会自动在子查询的表名前增加schema

## 示例模块
* [代码](https://github.com/baayso/ieasy-server/tree/master/src/main/java/com/baayso/springboot/demo)
* [MyBatis Mapper XML](https://github.com/baayso/ieasy-server/tree/master/src/main/resources/mybatis/demo)

## <span id = "test">测试：</span>
> 因为[配置了验证租户参数拦截器](https://github.com/baayso/ieasy-server/blob/master/src/main/java/com/baayso/springboot/config/web/MvcConfig.java#L38), 所以必须在**请求头**中增加`tenantCode`参数，参数值为`ieasy_tenant_1`
* http://localhost:8888/welcome
* http://localhost:8888/welcome/index  LayUI后台布局
* http://localhost:8888/welcome/index2  LayUI后台布局2
* http://localhost:8888/api/demo
* http://localhost:8888/api/demo/page?pageSize=100&pageNum=1
* http://localhost:8888/api/demo/deletes?id=1
* http://localhost:8888/api/demo/create
* http://localhost:8888/api/demo/creates
* http://localhost:8888/api/demo/update?id=7
* http://localhost:8888/api/demo/hello
* http://localhost:8888/api/demo/test  测试多租户(schema级)自动在表名前增加schema

## Dependencies:
* https://github.com/baayso/commons
