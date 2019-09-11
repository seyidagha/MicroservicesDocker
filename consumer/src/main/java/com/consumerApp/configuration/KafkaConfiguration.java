package com.consumerApp.configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.consumerApp.models.CustomDeserializer;
import com.consumerApp.models.Record;

@EnableKafka
@Configuration
public class KafkaConfiguration {

	@Value("${urlKafka}")
	private String urlKafka;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, urlKafka + ":9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		return props;
	}

	@Bean
	public ConsumerFactory<String, List<Record>> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new CustomDeserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, List<Record>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, List<Record>> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

	// @Bean
	// public Map<String, Object> producerConfigs() {
	// Map<String, Object> props = new HashMap<>();
	// props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	// props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	// StringSerializer.class);
	// props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	// JsonSerializer.class);
	// return props;
	// }
	//
	// @Bean
	// public ProducerFactory<String, List<Record>> producerFactory() {
	// return new DefaultKafkaProducerFactory<>(producerConfigs());
	// }
	//
	// @Bean
	// public KafkaTemplate<String, List<Record>> kafkaTemplate() {
	// return new KafkaTemplate<>(producerFactory());
	// }

}
