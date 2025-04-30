package com.example.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.example.kafka.ProducerService.NEW_SUBJECT;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { NEW_SUBJECT })
public class KafkaActivityApplicationTests {

	private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@KafkaListener(topics = "test-topic", groupId = "test-consumer")
	public void listen(String message) {
		messages.offer(message);
	}

	@Test
	public void testKafkaSendReceive() throws InterruptedException {
		String testMessage = "Hello Kafka!";
		kafkaTemplate.send("test-topic", testMessage);

		String received = messages.take(); // waits until a message is available
		assertEquals(testMessage, received);
	}
}

