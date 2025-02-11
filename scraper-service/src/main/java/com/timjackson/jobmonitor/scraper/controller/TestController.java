package com.timjackson.jobmonitor.scraper.controller;


import com.timjackson.jobmonitor.common.model.Company;
import com.timjackson.jobmonitor.scraper.service.CompanyService;
import org.springframework.web.bind.annotation.*;

// @RestController - Tells Spring this class handles HTTP requests
@RestController
// @RequestMapping - All URLs in this controller start with /api/test
@RequestMapping("/api/test")
public class TestController {
  private final CompanyService companyService;

  public TestController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @GetMapping("/hello")
  public String hello() {
    return "Hello from Scraper Service!";
  }

  @PostMapping("/company")
  public Company addCompany(@RequestParam String name, @RequestParam String workDayUrl) {
    return companyService.addCompany(name, workDayUrl);
  }
}
