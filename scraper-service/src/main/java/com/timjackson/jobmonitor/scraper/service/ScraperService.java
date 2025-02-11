package com.timjackson.jobmonitor.scraper.service;

import com.timjackson.jobmonitor.common.model.Company;
import com.timjackson.jobmonitor.common.model.JobPosting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



// Business logic
@Service
public class ScraperService {
    private final WebDriver webDriver;

    public ScraperService(WebDriver webDriver) {
      this.webDriver = webDriver;
    }

    public List<JobPosting> scrapeJobPostings(Company company) {
      List<JobPosting> newPostings = new ArrayList<>();
      try {
        webDriver.get(company.getWorkDayUrl());
        Thread.sleep(2000); // Wait for page to load

        // Find job listing
        // Need to be refine later
        List<WebElement> jobElements = webDriver.findElements(By.cssSelector(".job-listing"));

        for (WebElement jobElement : jobElements) {
          JobPosting posting = new JobPosting();
          posting.setCompany(company);
          posting.setJobTitle(jobElement.findElement(By.cssSelector(".job-title")).getText());
          posting.setJobUrl(jobElement.findElement(By.cssSelector("a")).getAttribute("href"));
          posting.setPostedDate(LocalDateTime.now());  // You'll need to parse actual date from page
          newPostings.add(posting);
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
      return newPostings;
    }

}
