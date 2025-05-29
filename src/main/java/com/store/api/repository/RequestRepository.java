package com.store.api.repository;
import com.store.api.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT request from Request request WHERE request.nationalCode = :nationalCode ORDER BY request.createdAt DESC" )
    Page<Request> findByNationalCode(String nationalCode, Pageable pageable);
}
