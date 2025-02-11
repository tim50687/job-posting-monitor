package com.timjackson.jobmonitor.scraper;


import org.springframework.boot.SpringApplication; // Used to launch the Spring Boot application.
// Marks this class as the main entry point for a Spring Boot application. It auto-configures Spring components.
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Enables support for scheduled tasks (like running the scraper daily).
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;




// Main entry point
// @SpringBootApplication - Marks this as a Spring Boot application
// @EnableScheduling - Allows us to schedule tasks (like daily scraping)
@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.timjackson.jobmonitor.common.repository")
@EntityScan(basePackages = {"com.timjackson.jobmonitor.common.model"}) // <-- ADD THIS
public class ScraperServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(ScraperServiceApplication.class, args);
  }
}