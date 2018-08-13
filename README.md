### 模块划分

- provider              数据库访问&接口实现
- provider-api        DTO&接口定义
- consumer           消费服务&对外暴露Http的服务

### 业务分层
- dao层
- service层
- controller层

### 测试代码

- dao层测试sql正确性。建议使用h2内存数据库作为测试数据库。
- service层使用mock验证本层逻辑。不关心dao层逻辑。
- controller层同service层。
- http对外暴露的就扣可以使用swagger来生成文档。

### 巧妙的设计

- service层读写分离。若服务器压力过大。可以优雅的服务降级，把写服务停掉。保证页面可读，提交失败。
- service层对外提供的数据模型是DTO不是PO。如果有必要修改PO，不会影响到所有的上层业务。
- 服务提供者捕获所有的异常，返回成功或者失败使用Response封装。异常不在RPC调用中处理。