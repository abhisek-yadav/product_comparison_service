package com.rel.pcs.datasource.impl.kafka.controller;

import com.rel.pcs.datasource.impl.kafka.producer.KafkaProductProducer;
import com.rel.pcs.model.Product;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaProducerController {

  @Autowired
  private KafkaProductProducer kafkaProducer;

  @PostMapping(path = "/api/produce/products", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Product> produceMessage(@Valid @RequestBody Product product) {

    String productID = UUID.randomUUID().toString();
    product.setProductID(productID);

    kafkaProducer.produceProduct(product);

    return ResponseEntity.ok(product);
  }

}
