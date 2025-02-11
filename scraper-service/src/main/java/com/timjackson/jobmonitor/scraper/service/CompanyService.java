package com.timjackson.jobmonitor.scraper.service;

import com.timjackson.jobmonitor.common.model.Company;
import com.timjackson.jobmonitor.common.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
  // This will be automatically injected by Spring
  // Spring automatically creates and provides an instance of a class where it's needed
  private final CompanyRepository companyRepository;

  // Constructor - Spring will use this to inject the repository
  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  // Method to add a new company
  public Company addCompany(String name, String workdayUrl) {
    Company company = new Company();
    company.setName(name);
    company.setWorkDayUrl(workdayUrl);
    return companyRepository.save(company);
  }
}
