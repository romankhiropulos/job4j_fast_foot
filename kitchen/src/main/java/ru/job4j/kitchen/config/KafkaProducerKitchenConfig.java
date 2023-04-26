package ru.job4j.kitchen.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.job4j.domain.dto.OrderDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerKitchenConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaServer;

    @Bean
    public Map<String, Object> producerKitchenConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<Long, OrderDto> producerKitchenFactory() {
        return new DefaultKafkaProducerFactory<>(producerKitchenConfigs());
    }

    @Bean
    public KafkaTemplate<Long, OrderDto> kafkaKitchenTemplate() {
        return new KafkaTemplate<>(producerKitchenFactory());
    }
}
