package com.timjackson.jobmonitor.scraper.scheduler;

import com.timjackson.jobmonitor.scraper.service.ScraperService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// Scheduler jobs
@Component
public class ScrapingScheduler {
   private final ScraperService scraperService;

   public ScrapingScheduler(ScraperService scraperService) {
     this.scraperService = scraperService;
   }

   @Scheduled(cron = "0 0 9 * * ?")
  public void schedulesScraping() {

   }
}
