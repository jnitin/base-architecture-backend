package com.backend.api.repositories;

import java.util.Optional;

import com.backend.api.domain.Parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Integer> {

    Optional<Parameter> findById(Integer id);
}
