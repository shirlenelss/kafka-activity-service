package com.example.kafka.service;

import com.example.kafka.config.SubjectConsumerGroups;
import com.example.kafka.config.SubjectTopics;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = SubjectTopics.NEW_SUBJECT_TOPIC, groupId = SubjectConsumerGroups.MY_CONSUMER_GROUP)
    public void listen(String message) {
        System.out.println("Received (NewSubject) message: " + message);
    }
}
