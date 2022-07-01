package com.tech.dpn.bidapplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    Logger LOG = LoggerFactory.getLogger(ConsumerService.class);
    private static final String TOPIC = "bidMessage";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(String message) {
        LOG.info(String.format("#### -> Producing message {}", message));
        this.kafkaTemplate.send(TOPIC, message);
        return "The message for bid has been sent";
    }
}
