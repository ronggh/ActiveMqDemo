package cn.alan.async_send;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;


public class AsyncProducer {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kQueueName = "async-send-queue";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
        // 设置异步消息投递
        factory.setUseAsyncSend(true);
        // 2. 通过连接工厂，获取连接connection并启动
        Connection connection = factory.createConnection();
        connection.start();
        // 3. 创建会话，有两个参数
        // 第一个是否开启事务，开启事务，则需要手动session.commit()
        // 第二个是签收,有三种方式:
        // Session.AUTO_ACKNOWLEDGE,自动签收，默认方式
        // Session.CLIENT_ACKNOWLEDGE，手动签收
        // Session.DUPS_OK_ACKNOWLEDGE,允许重复消息
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 4. 创建目的地（队列类型）
        Destination queue = session.createQueue(kQueueName);
        // 5. 创建消息的生产者
        // 注意：使用ActiveMQMessageProducer,并进行回调确认发送成功
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer)session.createProducer(queue);
        // 可以设置消息的持久或非持久模式,队列模式默认是持久化的
        // 非持久的消息在宕机时会丢失;持久化的消息在宕机时会保存
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        // 6. 通过生产者生产3条消息发送到队列中
        for (int i = 0; i< 3; i++){
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("async send Message------ " + i);
            // 设置消息ID，以确定每条消息发送是否成功
            textMessage.setJMSMessageID(UUID.randomUUID().toString());
            String messageID = textMessage.getJMSMessageID();

            // 8. 发送消息，使用回调
            producer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println(messageID + " has been sent OK.");
                }

                @Override
                public void onException(JMSException e) {
                    System.out.println(messageID + " failed sent to MQ.");
                }
            });

        }
        // 9. 释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("Async 消息发送完毕>>>>>>>");
    }
}
