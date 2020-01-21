package cn.alan.embed_broker;

import org.apache.activemq.broker.BrokerService;

public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        // ActiveMQ 支持在VM中通信，基于嵌入式的Broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();

    }
}
