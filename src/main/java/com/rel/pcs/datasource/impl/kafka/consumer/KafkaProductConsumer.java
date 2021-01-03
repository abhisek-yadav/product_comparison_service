package com.rel.pcs.datasource.impl.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rel.pcs.datasource.api.DataSourceProductConsumer;
import com.rel.pcs.datasource.impl.kafka.util.KafkaConstants;
import com.rel.pcs.dao.ProductDAO;
import com.rel.pcs.datasource.impl.kafka.config.KafkaConfig;
import com.rel.pcs.model.Product;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class KafkaProductConsumer implements DataSourceProductConsumer {

  @Autowired
  private KafkaConfig kafkaConfig;

  @Autowired
  private ProductDAO productDAO;

  @Override
  @Scheduled(fixedRate = 10000)
  public void consumeProduct() {

    List<Product> products = new ArrayList<>();
    boolean flag = true;

    try (org.apache.kafka.clients.consumer.KafkaConsumer<String, byte[]> consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(
        kafkaConfig.consumerProperties())) {

      consumer.subscribe(Collections.singleton(KafkaConstants.TOPIC_NAME));

      while (flag) {
        ConsumerRecords<String, byte[]> records = consumer.poll(Duration.ofMillis(100));
        records.forEach(record -> {
          ObjectMapper mapper = new ObjectMapper();
          try {
            products.add(mapper.readValue(new String(record.value()), Product.class));
          } catch (IOException e) {
            //log error message
          }
        });
        productDAO.batchUploadWithProductID(products);
        flag = false;
      }
    }
  }

}
