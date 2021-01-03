package com.rel.pcs.init;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchemaInitialization {

  private DataSource dataSource;

  @Autowired
  private SchemaInitialization(DataSource dataSource) {
    this.dataSource = dataSource;
    initializeSchema();
  }

  public void initializeSchema() {
    try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement()) {

      statement.execute("CREATE TABLE IF NOT EXISTS PRODUCTS (\n"
          + "    PRODUCT_ID VARCHAR(255) PRIMARY KEY,\n"
          + "    PRODUCT_NAME VARCHAR(255),\n"
          + "    PRODUCT_CATEGORY VARCHAR(255),\n"
          + "    PRODUCT_PRICE FLOAT(10))");

    } catch (SQLException e) {
      //log error message
    }
  }

}
