package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);

    @Transactional()
    @Query("SELECT DISTINCT r FROM User u join u.userProfiles p join p.routes r  WHERE u.id = :userId AND r.type =  1 ORDER BY r.description")
    List<Route> getMenus(@Param("userId") Integer userId);

    @Transactional()
    @Query("SELECT x FROM User u join u.userProfiles x WHERE u.id = :userId")
    List<UserProfile> getUserProfiles(@Param("userId") Integer id);

    @Transactional()
    
    @Query("SELECT x FROM User u join u.userProfiles x WHERE u.id = :userId")
    Page<UserProfile> getUserProfiles(@Param("userId") Integer id, Pageable pageRequest);
}
