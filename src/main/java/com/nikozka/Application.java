package com.nikozka;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

  public static void main(String[] args) {
    Flyway flyway =
        Flyway.configure()
            .dataSource("jdbc:postgresql://localhost:5432/reversed_olio", "postgres", "password")
            .locations("classpath:db.migration")
            .load();

    flyway.migrate();
    SpringApplication.run(Application.class, args);
  }
}
