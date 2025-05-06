package com.example.kafka.service;

import com.example.kafka.config.SubjectTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(SubjectTopics.NEW_SUBJECT_TOPIC, message);
        System.out.println("Sent message: " + message);
    }
}
