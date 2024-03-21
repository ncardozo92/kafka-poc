package ar.com.fluxit.kafkapoc.service;

import ar.com.fluxit.kafkapoc.config.KafkaConfigProperties;
import ar.com.fluxit.kafkapoc.dto.PaymentRequestDTO;
import ar.com.fluxit.kafkapoc.dto.PaymentResponseDTO;
import ar.com.fluxit.kafkapoc.dto.QueryResponseDTO;
import ar.com.fluxit.kafkapoc.entity.Payment;
import ar.com.fluxit.kafkapoc.util.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, Payment> payments = new HashMap<>();

    public PaymentResponseDTO executePayment(PaymentRequestDTO request) throws JsonProcessingException {
        String uuid = UUID.randomUUID().toString();

        Payment payment = new Payment();
        payment.setId(uuid);
        payment.setQr(request.getQr());
        payment.setAmount(request.getAmount());
        payment.setStatus(uuid.endsWith("F")? Status.REJECTED: Status.ACEPTED);

        payments.put(uuid, payment);

        kafkaTemplate.send(KafkaConfigProperties.getTopicPaymentExecution(), objectMapper.writeValueAsString(payment));

        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setId(uuid);

        return response;
    }

    public QueryResponseDTO queryForPayment(String id) throws JsonProcessingException {
        Payment payment = payments.get(id);

        QueryResponseDTO response = new QueryResponseDTO();
        response.setId(payment.getId());
        response.setStatus(payment.getStatus().toString());

        kafkaTemplate.send(KafkaConfigProperties.getTopicPaymentQueries(), "Anita lava la tina");

        return response;
    }

    @KafkaListener(topics = {"payment-execution", "payment-queries"}, groupId= "payments")
    public void getPaymentMessage(@Payload String message,
                                  @Header(KafkaHeaders.TOPIC) String topic){
        log.info("Message received: {} | topic: {}", message, topic);
    }
}
