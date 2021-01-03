package com.rel.pcs.datasource.impl.kafka.util;

public class KafkaConstants {

  private KafkaConstants() {
    throw new IllegalStateException("Constants class");
  }

  public static final String KAFKA_HOST = System.getenv("KAFKA_HOST");
  public static final String KAFKA_PORT = System.getenv("KAFKA_PORT");

  public static final String TOPIC_NAME = "test";

}
