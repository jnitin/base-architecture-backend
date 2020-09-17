package com.backend.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    public User findByEmail(String email);

    @Transactional()
    @Query("SELECT DISTINCT r FROM User u join u.userProfiles p join p.routes r  WHERE u.id = :userId AND r.type =  1 ORDER BY r.description")
    public List<Route> getMenus(@Param("userId") Integer userId);
}
