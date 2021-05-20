package com.backend.api.repositories;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.domain.enums.Profile;
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
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findByEmail(String email);

    @Query("SELECT DISTINCT r FROM User u join u.userProfiles p join p.routes r  WHERE u.id = :userId AND r.type = 'MENU'  ORDER BY r.description")
    List<Route> getMenus(@Param("userId") Long userId);

    @Transactional()
    @Query("SELECT x FROM User u join u.userProfiles x WHERE u.id = :userId")
    List<UserProfile> getUserProfiles(@Param("userId") Long id);

    @Transactional()
    
    @Query("SELECT x FROM User u join u.userProfiles x WHERE u.id = :userId")
    Page<UserProfile> getUserProfiles(@Param("userId") Long id, Pageable pageRequest);


    @Query("SELECT p from UserProfile p LEFT JOIN p.users u  WHERE u.id <> :userId OR u.id IS NULL" )
    Page<UserProfile> findUnlinkedProfiles(@Param("userId") Long id, Pageable pageable);
}
