package com.backend.api.repositories;

import java.util.Optional;

import com.backend.api.domain.Parametro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {

    Optional<Parametro> findById(Integer id);
}
