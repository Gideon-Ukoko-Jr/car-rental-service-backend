package com.group.crservice.repository;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.entities.UserEntity;
import com.group.crservice.domain.enums.RecordStatusConstant;
import com.group.crservice.exception.ConflictException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("select u from UserEntity u where u.isEmployee = true and u.recordStatus = com.group.crservice.domain.enums.RecordStatusConstant.ACTIVE")
    List<UserEntity> findAllEmployees();
}
