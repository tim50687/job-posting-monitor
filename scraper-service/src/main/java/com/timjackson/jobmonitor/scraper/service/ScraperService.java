package com.timjackson.jobmonitor.scraper.service;


import com.timjackson.jobmonitor.common.model.Company;
import com.timjackson.jobmonitor.common.model.JobPosting;
import com.timjackson.jobmonitor.common.repository.CompanyRepository;
import com.timjackson.jobmonitor.common.repository.JobPostingRepository;
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
    private final JobPostingRepository jobPostingRepository;
    public ScraperService(WebDriver webDriver, JobPostingRepository jobPostingRepository) {
      this.webDriver = webDriver;
      this.jobPostingRepository = jobPostingRepository;
    }

    public List<JobPosting> scrapeJobPostings(Company company) {
      List<JobPosting> newPostings = new ArrayList<>();
      boolean shouldContinue = true;
      try {
        webDriver.get(company.getWorkDayUrl());
        Thread.sleep(2000); // Wait for page to load

        while (shouldContinue) {
          // Find job listing
          // Need to be refine later
          WebElement jobList = webDriver.findElement(By.cssSelector("ul[aria-label^='Page 1']"));

          List<WebElement> jobElements = jobList.findElements(By.cssSelector("li"));

          for (WebElement jobElement : jobElements) {
            try {

              // Get job title link
              WebElement titleLink = jobElement.findElement(By.cssSelector("a[data-automation-id='jobTitle']"));
              // Get location
              WebElement locationElement = jobElement.findElement(By.cssSelector("div[data-automation-id='locations'] dd"));
              // Get posted date
              WebElement postedDate = jobElement.findElement(By.cssSelector("div[data-automation-id='postedOn'] dd"));

              String postedDateText = postedDate.getText();

              // Stop if we find a job posted more than 1 day ago
              if (postedDateText.contains("Days Ago") ||
                  postedDateText.contains("Weeks Ago") ||
                  postedDateText.contains("Months Ago")) {
                shouldContinue = false;
                break;
              }


              JobPosting posting = new JobPosting();
              posting.setCompany(company);
              posting.setJobTitle(titleLink.getText());
              posting.setJobUrl(titleLink.getAttribute("href"));
              posting.setLocation(locationElement.getText());
              posting.setPostedDate(postedDateText);


              // save to database
              JobPosting savedPosting = jobPostingRepository.save(posting);
              newPostings.add(posting);
            } catch (Exception e) {
              System.out.println("Error parsing job: " + e.getMessage());
            }

          }

          if (shouldContinue) {
            try {
              WebElement nextButton = webDriver.findElement(By.cssSelector("button[aria-label='next']"));
              if (nextButton.isEnabled()) {
                nextButton.click();
                Thread.sleep(2000); // Wait for next page to load
              } else {
                shouldContinue = false; // No more pages
              }
            } catch (Exception e) {
              shouldContinue = false; // Can't find next button or other error
              System.out.println("No more pages or error clicking next: " + e.getMessage());
            }
          }
        }


      } catch (Exception e) {
        e.printStackTrace();
      }
      return newPostings;
    }

}
