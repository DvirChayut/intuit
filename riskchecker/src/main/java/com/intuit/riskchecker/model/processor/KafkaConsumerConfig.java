package com.intuit.riskchecker.model.processor;

import java.util.HashMap;
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
import org.springframework.kafka.listener.ContainerProperties.AckMode;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.intuit.riskchecker.model.PaymentRequest;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;
	
	@Value("${spring.kafka.consumer.groupId}")
	private String groupId;
	
	 @Bean
	    public Map<String, Object> consumerConfigs() {
	        Map<String, Object> props = new HashMap<>();
	        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
	        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
	        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
	        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
	        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
	        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, "false");
	        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
	        return props;
	    }

	    @Bean
	    public ConsumerFactory<String, PaymentRequest> consumerFactory() {
	        return new DefaultKafkaConsumerFactory<>(
	                consumerConfigs(),
	                new StringDeserializer(),
	                new JsonDeserializer<>(PaymentRequest.class));
	    }

	    @Bean
	    public ConcurrentKafkaListenerContainerFactory<String, PaymentRequest> kafkaListenerContainerFactory() {
	        ConcurrentKafkaListenerContainerFactory<String, PaymentRequest> factory =
	                new ConcurrentKafkaListenerContainerFactory<>();
	        factory.setConsumerFactory(consumerFactory());
	        factory.getContainerProperties().setAckMode(AckMode.MANUAL_IMMEDIATE);
	        factory.getContainerProperties().setSyncCommits(true);
	       
	        
	        return factory;
	    }
}
