package cn.alan.bootactivemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;
import java.util.concurrent.Delayed;

@Component
public class QueueProducer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;
    @Autowired
    private Queue queue;

    public void produceMessage(){
        // 随机发送6位
        jmsTemplate.convertAndSend(queue,
                "Spring Boot send message:" + UUID.randomUUID().toString().substring(0,6));
    }

    // 间隔3秒，定时投递的方法
    @Scheduled(fixedDelay = 3000)
    public void produceMessageSchedule(){
        jmsTemplate.convertAndSend(queue,
                "Scheduled send message:" + UUID.randomUUID().toString().substring(0,6));
        System.out.println("Scheduled send OK.");
    }
}
