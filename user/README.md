# user用户服务
概述：用户服务

## 一、接口描述
1、注册-成功送积分，异步feign调用points服务<br>
2、登录-成功返回token，使用jwt生成<br>
3、通过token返回用户信息-请求头中携带token<br>
4、修改密码-update成功删除缓存<br>
5、退出登录-删除缓存中记录登录状态的token

## 二、补充说明
1、json中敏感信息使用spring security的Encryptors api实现对称加密<br>
2、MySQL存储密码使用apache的commons-lang3提供的安全散列算法sha实现加密，由于不可逆，故无法提供密码找回功能<br>
3、用户信息缓存，使用被动加载的方式<br>
4、实现SSO，缓存中存储token的version作为登录状态标记，使用拦截器在请求进入controller之前进行token认证——后续SSO独立为一个服务，其他需要SSO的在pom中引入即可<br>
5、使用spring security实现swagger的登录认证<br>
6、通过token获取用户信息接口，开放api给其他服务使用，实现信号量隔离@HystrixCommand<br>
7、自定义注解@Phone，@Password<br>
8、token使用jwt生成
