package cn.alan.springbootmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopicMainApp {
    public static void main(String[] args) {
        SpringApplication.run(TopicConsumer.class,args);
    }
}
