package com.timjackson.jobmonitor.common.model;

// mapping Java objects to database tables
import jakarta.persistence.*;
// generates boilerplate code
import java.time.LocalDateTime;
import lombok.Data;
// automatically set timestamps when a database record is created
import org.hibernate.annotations.CreationTimestamp;
// automatically update timestamps when a database record is modified
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalTime;

@Data
@Entity // JPA: allows you to define entity classes that correspond to database table
@Table(name = "companies")  // Specifies the table name in database
public class Company {
   @Id // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment id
   private Long id;

   @Column(nullable = false)
   private String name;

   @Column(nullable = false)
   private String workDayUrl;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;
}
