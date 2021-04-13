# Spring Cloud Alibaba

## 组件
- Sentinel: 流量控制
- Nacos: 服务注册中心，配置中心
- RocketMQ
- Dubbo
- Seata: 高性能微服务分布式事务
- Alibaba Cloud ACM
- Alibaba Cloud OSS
- Alibaba Cloud SchedulerX
- Alibaba Cloud SMS


## Nacos

### 注册中心
 1. Nacos作为注册中心
### 配置中心



## Sentinel


## dashboard
一个监控服务的控制台，我们可以在这里设置服务限流的规则，也可以监控服务当前的健康状态。  
注： 如果在Sentinel中配置了服务限流规则，则会与dashboard中配置的存在冲突，两者会有一个失效。  
dashboard服务的文档地址[dashboard](https://github.com/alibaba/Sentinel/wiki/Dashboard)  
dashboard的jar包地址 [download](https://github.com/alibaba/Sentinel/releases)

下载jar包之后需要运行jar包，运行命令如下：  
    > java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=spring-cloud-alibaba -jar sentinel-dashboard.jar

在运行jar的时候有几个JVM的参数需要注意：
- -Dserver.port=8080 : Spring Boot的参数， 用于指定 Spring Boot 服务端启动端口为 `8080`
- Dcsp.sentinel.dashboard.server=localhost:8080 : 向 Sentinel 接入端指定控制台的地址
- -Dproject.name=spring-cloud-alibaba : 向 Sentinel 指定应用名称，比如上面对应的应用名称就为 sentinel-dashboard






