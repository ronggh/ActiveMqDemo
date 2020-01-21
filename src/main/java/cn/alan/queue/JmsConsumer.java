package cn.alan.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class JmsConsumer {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kQueueName = "queue01";

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
        // 5. 创建消息的消费者
        MessageConsumer consumer = session.createConsumer(queue);
        // 6. 消费消息
        while (true) {
            // receive()不带参数的方法会一直等待
//            TextMessage message = (TextMessage) consumer.receive();
            TextMessage message = (TextMessage) consumer.receive(4000L);
            if (null != message) {
                System.out.println("消费者接收到的消息：" + message.getText());
            } else {
                break;
            }
        }
        // 7. 释放资源
        consumer.close();
        session.close();
        connection.close();

        System.out.println("消息接收完毕>>>>>>>");
    }
}
