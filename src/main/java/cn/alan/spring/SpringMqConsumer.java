package cn.alan.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpringMqConsumer {
    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        //
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //
        SpringMqConsumer consumer = (SpringMqConsumer)context.getBean("springMqConsumer");
        String retVal = (String) consumer.jmsTemplate.receiveAndConvert();

        System.out.println("消费者收到的消息：" + retVal);

    }
}
