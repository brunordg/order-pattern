package com.example.orderpoller.service;

import com.example.orderpoller.entity.Outbox;
import com.example.orderpoller.publisher.MessagePublisher;
import com.example.orderpoller.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderPollerService {

    private final OutboxRepository outboxRepository;

    private final MessagePublisher messagePublisher;

    @Scheduled(fixedRate = 60000)
    public void pollOutboxAndPublish() {
        log.info("Executing pollOutboxAndPublish");

        List<Outbox> unprocessedRecords = outboxRepository.findByProcessedFalse();
        log.info("Unprocessed record count: {}", unprocessedRecords.size());

        unprocessedRecords.forEach(outbox -> {
            try {

                messagePublisher.publish(outbox.getPayload());

                outbox.setProcessed(true);
                outboxRepository.save(outbox);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
