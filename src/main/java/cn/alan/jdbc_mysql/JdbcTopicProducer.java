package cn.alan.jdbc_mysql;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JdbcTopicProducer {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kTopicName = "jdbc-topic01";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 2. 通过连接工厂，获取连接connection并启动
        Connection connection = factory.createConnection();
        connection.start();
        // 3. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（主题类型）
        Topic topic = session.createTopic(kTopicName);
        // 5. 创建消息的生产者
        MessageProducer producer = session.createProducer(topic);
        // !!! 一定要开启持久化 ！！！
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 6. 通过生产者生产3条消息发送到主题中
        for (int i = 0; i< 3; i++){
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("Jdbc-Topic ------ " + i);
            // 8. 发送消息
            producer.send(textMessage);
        }
        // 9. 释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("Jdbc主题消息发送到MQ完毕>>>>>>>");
    }
}
