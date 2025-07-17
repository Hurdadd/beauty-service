package com.store.api.repository;
import com.store.api.DTO.RequestReportDTO;
import com.store.api.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT request from Request request WHERE request.nationalCode = :nationalCode ORDER BY request.createdAt DESC")
    Page<Request> findByNationalCode(String nationalCode, Pageable pageable);

    @Query("""
        SELECT new com.store.api.DTO.RequestReportDTO(r.typeOfBeautyService, COUNT(r.id))
        FROM Request r
        WHERE r.createdAt >= :startDate
        GROUP BY r.typeOfBeautyService
        ORDER BY COUNT(r.id) DESC
    """)    Page<RequestReportDTO> countingRequestReports(@Param("startDate") LocalDateTime startDate , Pageable pageable);

}
