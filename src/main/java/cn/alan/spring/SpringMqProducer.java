package cn.alan.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class SpringMqProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        //
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //
        SpringMqProducer producer = (SpringMqProducer)context.getBean("springMqProducer");
        //
        producer.jmsTemplate.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("Spring和ActiveMQ的整合发送的消息...");
            }
        });

        //
        System.out.println("Spring send MQ message OK.");

    }
}

