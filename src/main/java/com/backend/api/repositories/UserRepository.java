package com.backend.api.repositories;

import com.backend.api.domain.Company;
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
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
  User findByEmail(String email);

  @Query("SELECT up FROM User u join u.userProfiles up WHERE u.id = :userId")
  List<UserProfile> getUserProfiles(@Param("userId") Long id);

  @Query("SELECT x FROM User u join u.userProfiles x WHERE u.id = :userId")
  Page<UserProfile> getUserProfiles(@Param("userId") Long id, Pageable pageRequest);

  @Query("SELECT c FROM User u join u.companies c WHERE u.id = :userId")
  Page<Company> getUserCompanies(@Param("userId") Long id, Pageable pageable);
}
