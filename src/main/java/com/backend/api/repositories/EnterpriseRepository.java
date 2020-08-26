package com.backend.api.repositories;

import com.backend.api.domain.Enterprise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, Integer>{

}
