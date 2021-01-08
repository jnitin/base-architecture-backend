package com.backend.api.repositories;

import com.backend.api.domain.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer>, JpaSpecificationExecutor<Parameter> {

    Optional<Parameter> findById(Integer id);
}
