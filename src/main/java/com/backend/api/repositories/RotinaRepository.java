package com.backend.api.repositories;

import com.backend.api.domain.Rotina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotinaRepository extends JpaRepository<Rotina, Integer>{

}
