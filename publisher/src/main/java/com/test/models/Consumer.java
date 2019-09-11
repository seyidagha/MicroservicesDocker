// package com.test.models;
//
// import java.util.List;
//
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;
//
// @Service
// public class Consumer {
// private final Logger logger = LoggerFactory.getLogger(Consumer.class);
//
// @KafkaListener(topics = "data", groupId = "group_id")
// public void consume(List<Record> message) {
// logger.info(String.format("$$ -> Consumed Message -> %s", "message"));
// System.out.println(message);
// }
// }