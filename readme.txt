这里有多个微服务模块：user 用户中心 、article 文章中心 、付款中心 pay;
这些模块单独访问都是需要登录的，
本项目要做一个单点登录

cas认证的服务端使用cas框架已经提供的war包，
如果想做自定义的内容，如登录页面、用户在数据库的验证等等，可以做针对性开发(tomact-cas-server就是已配置好的cas服务端)
cas端在验证用户登录时，可使用自定义验证，配置数据库验证
