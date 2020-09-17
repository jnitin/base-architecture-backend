package com.backend.api.repositories;

import com.backend.api.domain.Route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route, Integer>, JpaSpecificationExecutor<Route> {

}
