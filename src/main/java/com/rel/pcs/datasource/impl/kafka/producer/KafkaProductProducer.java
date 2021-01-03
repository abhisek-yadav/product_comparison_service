package com.rel.pcs.datasource.impl.kafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rel.pcs.datasource.api.DataSourceProductProducer;
import com.rel.pcs.datasource.impl.kafka.config.KafkaConfig;
import com.rel.pcs.datasource.impl.kafka.util.KafkaConstants;
import com.rel.pcs.model.Product;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaProductProducer implements DataSourceProductProducer {

  @Autowired
  private KafkaConfig kafkaConfig;

  public Product produceProduct(Product product) {

    try (Producer<String, byte[]> producer = new org.apache.kafka.clients.producer.KafkaProducer<>(
        kafkaConfig.producerProperties())) {

      ObjectMapper mapper = new ObjectMapper();
      byte[] val = mapper.writeValueAsString(product).getBytes();

      producer
          .send(new ProducerRecord<>(KafkaConstants.TOPIC_NAME, product.getProductCategory(), val),
              (rm, ex) -> {
                if (ex != null) {
                  throw new RuntimeException(ex);
                }
              });

    } catch (JsonProcessingException e) {
      //log the error message
    }
    return product;
  }

}
