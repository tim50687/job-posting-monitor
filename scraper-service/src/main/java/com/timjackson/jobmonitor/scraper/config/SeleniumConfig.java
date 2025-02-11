package com.timjackson.jobmonitor.scraper.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Marks this as a Spring configuration class, tell Spring that this class contains Beans
public class SeleniumConfig {

    @Bean
    //Bean - Spring will
    //Call this method
    //Store the returned object
    //Give that same object to any class that needs a WebDriver
    public WebDriver webDriver() {
      // Setup ChromeDriver
      WebDriverManager.chromedriver().setup();

      ChromeOptions options = new ChromeOptions();

      options.addArguments("--headless");
      options.addArguments("--disable-gpu");
      options.addArguments("--no-sandbox");

      return new ChromeDriver(options);

    }

}
