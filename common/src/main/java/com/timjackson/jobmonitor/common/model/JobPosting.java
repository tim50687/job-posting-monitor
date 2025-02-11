package com.timjackson.jobmonitor.common.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "job_postings")
public class JobPosting {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @Column(nullable = false)
  private String jobTitle;

  @Column(nullable = false)
  private String jobUrl;

  private String location;


  @Column(nullable = false)
  private LocalDateTime postedDate;

  @CreationTimestamp
  private LocalDateTime createdAt;

  @UpdateTimestamp
  private LocalDateTime updatedAt;
}
