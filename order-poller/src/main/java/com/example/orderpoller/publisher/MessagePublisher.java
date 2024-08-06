package com.example.orderpoller.publisher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${order.poller.topic.name}")
    private String topicName;

    public void publish(String payload) {
        kafkaTemplate.send(topicName, payload).whenComplete((result, ex) -> {
            if (ex != null) {
                log.info("Sent message =[{}] with offset=[{}]", ex.getMessage(), result.getRecordMetadata().offset());
            } else {
                log.info("Unable to send message =[{}] with offset=[{}] and partition=[{}]", payload, result.getRecordMetadata().offset(), result.getRecordMetadata().partition());
            }
        });
    }
}
