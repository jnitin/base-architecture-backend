package com.backend.api.repositories;

import com.backend.api.domain.Rota;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Integer>{

}