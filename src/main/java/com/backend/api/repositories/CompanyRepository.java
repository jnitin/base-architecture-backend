package com.backend.api.repositories;

import com.backend.api.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>, JpaSpecificationExecutor<Company> {

  @Transactional()
    @Query("SELECT DISTINCT c FROM User u JOIN u.companies c WHERE u.id = :userId")
  List<Company> getMenus(@Param("userId") Long userId);
}
