package cn.alan.cluster;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class ClusterProducer {
    //
    private final static String kActiveMqUrl = "failover:(tcp://192.168.154.101:61616,tcp://192.168.154.102:61616,tcp://192.168.154.103:61616)";
    private final static String kQueueName = "cluster-queue01";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 2. 通过连接工厂，获取连接connection并启动
        Connection connection = factory.createConnection();
        connection.start();
        // 3. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4. 创建目的地（队列类型）
        Destination queue = session.createQueue(kQueueName);
        // 5. 创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        // 可以设置消息的持久或非持久模式,队列模式默认是持久化的
        // 非持久的消息在宕机时会丢失;持久化的消息在宕机时会保存
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 6. 通过生产者生产3条消息发送到队列中
        for (int i = 0; i< 3; i++){
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("Cluster Message------ " + i);

            // 8. 发送消息
            producer.send(textMessage);

        }
        // 9. 释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("Cluster消息发送完毕>>>>>>>");
    }
}
