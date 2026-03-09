# credit
项目介绍

创新学分管理系统是一个基于 Spring Boot、MyBatis 和 Thymeleaf 开发的 Web 应用系统，用于管理学生的创新学分申请、审核和统计。系统分为教师端和学生端，提供了完整的学分管理功能。

环境配置

JDK 17 或更高版本  
MySQL 8.0 或更高版本  
Maven 3.8 或更高版本  
数据库配置：  
创建数据库：CREATE DATABASE coursedesign1;
修改 src/main/resources/application.properties 文件中的数据库连接信息

运行步骤

构建项目：mvn clean install  
运行项目：mvn spring-boot:run  
访问系统：  
登录页面：http://localhost:8086/login  
教师端：http://localhost:8086/teacher/login  
学生端：http://localhost:8086/student/login  

功能说明

教师端功能：  
用户管理：教师登录、退出  
学生管理：查看学生列表  
班级管理：查看班级列表、创建班级  
学分类别管理：查看学分类别、创建学分类别  
学分申请审核：查看待审核申请、审核申请（通过/不通过）   
已审核申请：查看已审核的申请记录  
统计数据：显示待审核申请数量、学生总数、班级总数  
学生端功能：  
用户管理：学生登录、退出  
学分申请：提交创新学分申请  
申请记录：查看个人申请记录  
学分统计：查看已获得的创新学分总数  

技术栈

后端：Spring Boot、MyBatis 
前端：Thymeleaf、Bootstrap  
数据库：MySQL  
构建工具：Maven 