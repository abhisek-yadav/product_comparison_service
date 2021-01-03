package com.rel.pcs.configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan
public class DatabaseConfig {

  private final String PROPERTY_DRIVER = "org.postgresql.Driver";
  private final String PROPERTY_URL = "jdbc:postgresql://db:5432/postgres?currentSchema=postgres&user=postgres&password=postgres";

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setUrl(PROPERTY_URL);
    ds.setDriverClassName(PROPERTY_DRIVER);
    ds.setSchema("public");
    return ds;
  }

}
