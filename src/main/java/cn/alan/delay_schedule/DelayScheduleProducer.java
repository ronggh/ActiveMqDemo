package cn.alan.delay_schedule;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;


public class DelayScheduleProducer {
    //
    private final static String kActiveMqUrl = "tcp://192.168.154.101:61616";
    private final static String kQueueName = "delay-schedule-queue";

    public static void main(String[] args) throws Exception {
        // 1. 创建连接工厂，按照给定的Url地址和默认的用户名、密码
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(kActiveMqUrl);
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
        MessageProducer producer = session.createProducer(queue);

        // 设置延时和定时属性
        long delay = 3 * 1000L;
        long period = 4 * 1000L;
        int repeat = 5;

        // 6. 通过生产者生产3条消息发送到队列中
        for (int i = 0; i< 3; i++){
            // 7. 创建消息
            TextMessage textMessage = session.createTextMessage("Delay and Schedule Message------ " + i);
            // 设置消息属性
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);
            // 8. 发送消息
            producer.send(textMessage);
        }
        // 9. 释放资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("Delay and Schedule 消息发送完毕>>>>>>>");
    }
}
