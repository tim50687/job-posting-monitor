package com.timjackson.jobmonitor.scraper.controller;


import com.timjackson.jobmonitor.common.model.Company;
import com.timjackson.jobmonitor.common.model.JobPosting;
import com.timjackson.jobmonitor.common.repository.CompanyRepository;
import com.timjackson.jobmonitor.scraper.service.CompanyService;
import com.timjackson.jobmonitor.scraper.service.ScraperService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @RestController - Tells Spring this class handles HTTP requests
@RestController
// @RequestMapping - All URLs in this controller start with /api/test
@RequestMapping("/api")
public class TestController {
  private final CompanyService companyService;
  private final ScraperService scraperService;
  private final CompanyRepository companyRepository;

  public TestController(CompanyService companyService, ScraperService scraperService,CompanyRepository companyRepository) {
    this.companyService = companyService;
    this.scraperService = scraperService;
    this.companyRepository = companyRepository;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello from Scraper Service!";
  }

  @PostMapping("/companies")
  public Company addCompany(@RequestParam String name, @RequestParam String workDayUrl) {
    return companyService.addCompany(name, workDayUrl);
  }

  @PostMapping("/companies/{companyId}/scrape")
  public List<JobPosting> scrapeCompanyJobs(@PathVariable Long companyId) {
    Company company = companyRepository.findById(companyId)
        .orElseThrow(() -> new RuntimeException("Company not found with id: " + companyId));
    return scraperService.scrapeJobPostings(company);
  }
}
