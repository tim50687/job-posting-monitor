package com.timjackson.jobmonitor.common.repository;

import com.timjackson.jobmonitor.common.model.JobPosting;
import com.timjackson.jobmonitor.common.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
  // Find all job postings for a specific company
  List<JobPosting> findByCompany(Company company);

  // Find by job title containing certain text (case-insensitive)
  List<JobPosting> findByJobTitleContainingIgnoreCase(String titlePart);
}
