package cn.alan.sprintbootmq;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Configuration
public class ConfigBean {
    @Value("${myTopic}")
    private String topicName;

    @Bean
    public Topic topic(){
      return new ActiveMQTopic(topicName);
    }
}
