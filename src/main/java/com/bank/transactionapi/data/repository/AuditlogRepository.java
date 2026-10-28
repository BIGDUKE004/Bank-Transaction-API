package com.bank.transactionapi.data.repository;

import com.bank.transactionapi.data.models.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditlogRepository extends JpaRepository<AuditLog, Long> {
}
