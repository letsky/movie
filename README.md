# A MOVIE电影购票网站
实训项目，采用Spring Boot，MyBatis，Thymeleaf框架。实现功能有：登录/注册，评分，购票，评论。

## 开始
进入项目目录，输入`mvn spring-boot:run`，访问localhost:8080即可。

## 目录
```text
├─src
│  ├─main
│  │  ├─java
│  │  │  └─cn
│  │  │      └─letsky
│  │  │          └─movie
│  │  │              ├─advice       //aop及统一异常处理
│  │  │              ├─configure    //项目配置相关
│  │  │              ├─constant     //常量
│  │  │              ├─controller   //控制器
│  │  │              │  └─api       //Restful API
│  │  │              ├─entity       //数据库实体
│  │  │              ├─exception    //异常
│  │  │              ├─form         //表单实体
│  │  │              ├─repository   //持久层
│  │  │              ├─service      //服务层
│  │  │              │  └─Impl
│  │  │              ├─util         //工具
│  │  │              └─vo           //展示层
│  │  └─resources
│  │      ├─mapper                  //MyBatis的xml文件
│  │      ├─static                  //静态资源
│  │      │  ├─css
│  │      │  │  ├─external
│  │      │  │  └─img
│  │      │  ├─fonts
│  │      │  ├─images
│  │      │  │  ├─components
│  │      │  │  ├─gallery
│  │      │  │  │  ├─large
│  │      │  │  │  └─thumb
│  │      │  │  ├─icons
│  │      │  │  ├─payment
│  │      │  │  └─rate
│  │      │  └─js
│  │      │      └─external
│  │      └─templates               //模板
│  │          └─fragment            //公有模板
│  └─test                           //测试
│      └─java
│          └─cn
│              └─letsky
│                  └─movie
│                      ├─repository
│                      └─service
│                          └─Impl

```