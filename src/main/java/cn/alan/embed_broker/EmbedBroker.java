package cn.alan.embed_broker;

import org.apache.activemq.broker.BrokerService;

/***
 * 可以用此来启动内嵌的ActiveMQ Broker
 */
public class EmbedBroker {
    public static void main(String[] args) throws Exception {
        // ActiveMQ 支持在VM中通信，基于嵌入式的Broker
        BrokerService brokerService = new BrokerService();
        brokerService.setUseJmx(true);
        brokerService.addConnector("tcp://localhost:61616");
        brokerService.start();
    }
}
