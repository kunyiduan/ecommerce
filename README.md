# 基于Spring cloud的项目
概述：基于对spring cloud的理解，实现可直接复用的分布式项目<br>
技术：spring cloud,spring security,eureka,feign,hystrix,ribbon,swagger,jwt,lombok,druid,mybatis,mybatis-plus,mysql,redis,异步调用,对称加密<br>
## 零、注册中心服务
单节点eureka注册中心<br>
## 一、用户服务
注册-成功后异步增加积分<br>
登录-成功后返回token，基于JWT生成token<br>
退出登录-删除缓存中登录状态记录<br>
修改密码-使用对称加密技术<br>
通过token获取用户信息<br>
使用缓存，实现单点登录功能<br>
## 二、积分服务
添加积分<br>
## 三、商品服务
<br>
