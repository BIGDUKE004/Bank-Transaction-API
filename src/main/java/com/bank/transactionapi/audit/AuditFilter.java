package com.bank.transactionapi.audit;

import com.bank.transactionapi.data.models.AuditLog;
import com.bank.transactionapi.data.repository.AuditlogRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class AuditFilter extends OncePerRequestFilter {
    private AuditlogRepository auditLogRepository;
    public  AuditFilter(AuditlogRepository auditlogRepository) {
        this.auditLogRepository = auditlogRepository;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } finally {
            AuditLog auditLog = new AuditLog();

            auditLog.setCreatedAt(LocalDateTime.now());
            auditLog.setPath(request.getRequestURI());
            auditLog.setHttpMethod(request.getMethod());
            auditLog.setStatusCode(response.getStatus());

            this.auditLogRepository.save(auditLog);
        }
    }
}
