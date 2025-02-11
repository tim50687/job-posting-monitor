package com.timjackson.jobmonitor.common.repository;

import com.timjackson.jobmonitor.common.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// @Repository - Marks this as a Spring Data repository
// JpaRepository<Entity, ID> - Provides basic CRUD operations
// Company - The entity type we're managing
// Long - The type of the entity's ID field
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
  // Spring Data JPA will automatically implement basic methods like:
  // save(), findById(), findAll(), delete(), etc.

  // Custom method to find company by name
  // Spring will automatically create the implementation based on the method name
  Company findByName(String name);
}
