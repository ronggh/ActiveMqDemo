# ActiveMqDemo
## cn.alan.queue 队列模式
### JmsProducer 消息生产者
### JmsConsumer 消息消费者，同步阻塞方式
### JmsConsumerListen 消息消费者，通过监听方式进行
## cn.alan.topic 主题模式
### JmsTopicProducer 消息生产者，主题模式
### JmsTopicConsumer 消息消费者，主题模式
## cn.alan.topic_persistent 持久化的topic
### JmsTopicProducerPersistent 持久化topic的生产者
## cn.alan.embed_broker 
### EmbedBroker 嵌入式的Broker，可用此来代替ActiveMQ用于测试
## cn.alan.nio  NIO协议下的编码
    + 需要先修改配置文件
    + ActiveMQ安装目录下conf/activemq.xml中<transportConnectors>节中增加
    `
    <transportConnector name="nio" uri="nio://0.0.0.0:61618?trace=true"/> 
    `
### NioProducer 使用NIO协议的生产者
    + 需要修改协议地址为
    `
    nio://192.168.154.101:61618
    `
### NioConsumer 使用Nio 协议的消费者
    + 需要修改协议地址为
        `
        nio://192.168.154.101:61618
     
## cn.alan.jdbc_mysql 使用MySQL进行持久化
### JdbcQueueProducer 队列模式的生产者
### JdbcQueueConsumer 队列模式的消费者
### JdbcTopicProducer 主题模式的生产者
### JdbcTopicConsumer 主题模式的消费者
## cn.alan.cluster 使用MQ集群
### ClusterProducer 
### ClusterConsumer
## an.alan.async_send 异步发送
### AysncProducer
## cn.alan.delay_schedule 延时投递和定时投递
## cn.alan.spring 与Spring整合
### resources目录下，创建applicatonContext.xml配置文件
### SpringMqProducer Spring整合ActiveMQ的生产者
### SpringMqConsumer Spring整合ActiveMQ的消费者
### MyMessageListener 实现的消息监听类
## SpringBootMqProducer 新建一个Maven模块，实现SpringBoot与MQ的整合的生产者
### application.yml resources目录下，配置文件
### ConfigBean 配置Bean
### QueueProducer 队列生产者类
### MainApp 主启动类
### TestMQ 测试类，在test/java/cn.alan.bootactivemq包下
## SpringBootMqConsumer 新建一个Maven模块，实现SpringBoot与MQ的整合的消费者
### application.yml resources目录下，配置文件
### QueueConsumer 队列消费者类
### MainApp 主启动类 
 



