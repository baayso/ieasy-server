# Spring Boot Demo  
Spring Boot 脚手架项目

[![Jdk Version](https://img.shields.io/badge/JDK-1.8+-green.svg)](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build Status](https://travis-ci.org/baayso/spring-boot-demo.svg?branch=master)](https://travis-ci.org/baayso/spring-boot-demo)

## 环境要求：
* Jdk 8.0+
* Maven 3.2+
* 开发环境使用H2数据库，测试环境和生产环境使用MySQL数据库，请查看[配置文件](https://github.com/baayso/spring-boot-demo/tree/master/src/main/resources/config)

## 快速开始（开发环境）：
1. git clone https://github.com/baayso/spring-boot-demo.git
2. ./build.bat (Windows) Or ./build.sh (Linux/Mac OS)
3. ./bin/start.bat 8888 dev (Windows) Or ./bin/start.sh 8888 dev (Linux/Mac OS)

## 快速开始（测试环境）：
1. git clone https://github.com/baayso/spring-boot-demo.git
2. 安装 MySQL 5.5+
3. 执行 [SQL 脚本](https://github.com/baayso/spring-boot-demo/blob/master/sql/springbootdemo.sql)
4. ./build.bat (Windows) Or ./build.sh (Linux/Mac OS)
5. ./bin/start.bat 8888 test (Windows) Or ./bin/start.sh 8888 test (Linux/Mac OS)

## 快速开始（生产环境）：
1. git clone https://github.com/baayso/spring-boot-demo.git
2. 安装 MySQL 5.5+
3. 执行 [SQL 脚本](https://github.com/baayso/spring-boot-demo/blob/master/sql/springbootdemo.sql)
4. ./build.bat (Windows) Or ./build.sh (Linux/Mac OS)
5. ./bin/start.bat (Windows) Or ./bin/start.sh (Linux/Mac OS)

## Dependencies:
https://github.com/baayso/commons  
