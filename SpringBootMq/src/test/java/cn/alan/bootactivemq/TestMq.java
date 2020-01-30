package cn.alan.bootactivemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = MainApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestMq {
    @Autowired
    private QueueProducer producer;

    @Test
    public void testSend() {
        producer.produceMessage();
    }
}
