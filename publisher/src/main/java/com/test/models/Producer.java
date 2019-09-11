package com.test.models;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
	private static final Logger logger = LoggerFactory.getLogger(Producer.class);
	private static final String TOPIC = "data";
	@Autowired
	private KafkaTemplate<String, List<Record>> kafkaTemplate;

	public void sendMessage(List<Record> list) {
		logger.info(String.format("$$ -> Producing message --> %s", "message"));
		// Message<List<Record>> message =
		// MessageBuilder.withPayload(list).setHeader(KafkaHeaders.TOPIC,
		// TOPIC).build();

		this.kafkaTemplate.send(TOPIC, list);
	}
}