package ar.com.fluxit.kafkapoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfigProperties {

    private static String kafkaServer;


    private static String kafkaPort;


    private static String topicPaymentExecution;


    private static String topicPaymentQueries;

    public KafkaConfigProperties(
            @Value("${kafka.server}") String kafkaServer,
            @Value("${kafka.port}") String kafkaPort,
            @Value("${kafka.topic.payment.execution}") String topicPaymentExecution,
            @Value("${kafka.topic.payment.queries}") String topicPaymentQueries){

        this.kafkaServer = kafkaServer;
        this.kafkaPort = kafkaPort;
        this.topicPaymentExecution = topicPaymentExecution;
        this.topicPaymentQueries = topicPaymentQueries;
    }


    public static String getTopicPaymentExecution() {
        return topicPaymentExecution;
    }

    public static String getTopicPaymentQueries() {
        return topicPaymentQueries;
    }

    public static String getBootstrapServer(){
        return String.format("%s:%s", kafkaServer, kafkaPort);
    }
}
