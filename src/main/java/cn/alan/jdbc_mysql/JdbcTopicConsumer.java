package cn.alan.jdbc_mysql;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class JdbcTopicConsumer {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kTopicName = "jdbc-topic01";

    public static void main(String[] args) throws Exception {
        System.out.println("我是JDBC-1号消费者");
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 2. 通过连接工厂，获取连接connection并启动
        Connection connection = factory.createConnection();
        // 开启前需要设置client_id
        connection.setClientID("jdbc-consume-1");
        connection.start();
        // 3. 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4. 创建目的地（主题类型）
        Topic topic = session.createTopic(kTopicName);
        // 5. 创建消息的消费者
        // 这种是普通订阅
        //MessageConsumer consumer = session.createConsumer(topic);
        // 这是永久订阅
        MessageConsumer consumer = session.createDurableSubscriber(topic,"jdbc-topic-1");
        //
        // 6. 设置消息消费的监听者
        // 使用lambda表达式方式写
        consumer.setMessageListener((message) ->{
            if( message instanceof TextMessage){
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收到的JDBC-Topic消息是：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 7. 暂停
        System.in.read();
        // 8. 释放资源
        consumer.close();
        session.close();
        connection.close();
    }
}
