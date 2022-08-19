package com.group.crservice.repository;

import com.group.crservice.domain.entities.RoleEntity;
import com.group.crservice.domain.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(ERole name);

    boolean existsByName(ERole name);
}
