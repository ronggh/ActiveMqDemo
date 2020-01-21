package cn.alan.topic_persistent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsTopicProducerPersistent {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kTopicName = "topic-persistent";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 2. 通过连接工厂，获取连接connection
        // 注：不能在此启动，要在设置了持久化之后再启动
        Connection connection = factory.createConnection();

        // 3. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（主题类型）
        Destination topic = session.createTopic(kTopicName);
        // 5. 创建消息的生产者
        MessageProducer producer = session.createProducer(topic);
        // 设置持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        // 设置持久化之后再启动
        connection.start();
        // 6. 通过生产者生产3条消息发送到主题中
        for (int i = 0; i< 3; i++){
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("Persistent Topic ------ " + i);
            // 8. 发送消息
            producer.send(textMessage);
        }
        // 9. 释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("持久化主题消息发送到MQ完毕>>>>>>>");
    }
}
