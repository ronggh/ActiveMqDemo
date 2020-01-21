package cn.alan.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class JmsConsumerListen {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kQueueName = "queue01";

    public static void main(String[] args) throws Exception {
        System.out.println("我是1号消费者");
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
        // 6. 设置消息消费的监听者
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                if( null != message && message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println("接收到的消息是：" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
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
