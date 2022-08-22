package com.group.crservice.repository;

import com.group.crservice.domain.entities.TransactionEntity;
import com.group.crservice.domain.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByStatus(BookingStatus status);
}
