package com.tech.dpn.bidapplication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.dpn.bidapplication.entity.Buyer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    Logger LOG = LoggerFactory.getLogger(ConsumerService.class);

    @Autowired
    BuyerService buyerService;

    //@KafkaListener(topics = "bidMessage", groupId = "group_id")
    public void consume(String message)  {
        LOG.info("Inside the consumer of topic bidMessage");
        try {
        	LOG.info("pass the message to buyerService.bidAProduct message = {}",message);
        }catch (Exception ex){
            LOG.error("Exception while consume the message: ",ex.getMessage());
        }
    }
}
