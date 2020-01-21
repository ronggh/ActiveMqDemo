package cn.alan.topic_persistent;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class JmsTopicConsumerPersistent {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kTopicName = "topic-persistent";

    public static void main(String[] args) throws Exception {
        System.out.println("我是1号消费者");
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 2. 通过连接工厂，获取连接connection
        // 注：不能再此启动，还需要设置客户端ID
        Connection connection = factory.createConnection();
        connection.setClientID("consumer1");

        // 3. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（主题类型）
        Topic topic = session.createTopic(kTopicName);
        // 5. 需要设置订阅者
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "remark....");
        // 在这一步启动
        connection.start();
        // 使用订阅者接收消息
        Message message = subscriber.receive();
        while(null != message){
            TextMessage textMessage = (TextMessage)message;
            System.out.println("收到的持久化的TopicMessage：" + textMessage.getText());
            // 接收下一条消息，等待5s则结束
            message = subscriber.receive(5000L);
            // 用下面这种方式，则会一直等待，程序不会结束
//            message = subscriber.receive();
        }
        //
        session.close();
        connection.close();
    }
}
