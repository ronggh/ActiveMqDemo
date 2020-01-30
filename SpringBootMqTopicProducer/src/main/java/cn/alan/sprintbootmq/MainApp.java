package cn.alan.sprintbootmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(TopicProducer.class,args);
    }
}
