package com.rel.pcs.datasource.impl.kafka.config;

import com.rel.pcs.datasource.impl.kafka.util.KafkaConstants;
import java.util.Properties;
import org.springframework.stereotype.Component;

@Component
public class KafkaConfig {

  public Properties producerProperties() {
    Properties props = new Properties();
    props.put("bootstrap.servers", KafkaConstants.KAFKA_HOST + ":" + KafkaConstants.KAFKA_PORT);
    props.put("acks", "all");
    props.put("retries", 2);
    props.put("batch.size", 16384);
    props.put("linger.ms", 1);
    props.put("buffer.memory", 33554432);
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");

    return props;
  }

  public Properties consumerProperties() {
    Properties props = new Properties();
    props.put("bootstrap.servers", KafkaConstants.KAFKA_HOST + ":" + KafkaConstants.KAFKA_PORT);
    props.put("group.id", "test");
    props.put("enable.auto.commit", "true");
    props.put("auto.commit.interval.ms", "1000");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");

    return props;
  }

}
