package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

    @Transactional()
    @Query("SELECT r from UserProfile p JOIN p.routes r WHERE p.id = :profileId")
    Page<Route> findLinkedRoutes(@Param("profileId") Long profileId, Pageable pageable);

    @Query("SELECT r FROM Route r WHERE r NOT IN(SELECT r2 FROM UserProfile p JOIN p.routes r2 WHERE p.id = :profileId)")
    Page<Route> findUnlinkedRoutes(@Param("profileId") Long routeId, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u NOT IN (SELECT u2 FROM UserProfile p JOIN p.users u2 WHERE p.id = :profileId)")
    Page<User> findUnlinkedUsers(@Param("profileId") Long id, Pageable pageable);

    @Query("SELECT p from UserProfile p WHERE p.id IN (:ids)")
    List<UserProfile> findByIds(@Param("ids") List<Long> ids);

    @Query("SELECT u from UserProfile p join p.users u WHERE p.id = :id")
    Page<User> findUsersById(@Param("id") Long id, Pageable pageable);
}
