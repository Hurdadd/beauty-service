package com.store.api.service;

import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.RequestReportDTO;
import com.store.api.DTO.ResponseRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface RequestService {
    ResponseRequestDTO createRequest(CreateRequestDTO createRequestDTO);
    void deleteRequest(Long id);
    Page<ResponseRequestDTO> findByNationalCode(String nationalCode, Pageable pageable);
    ResponseRequestDTO updateRequest(Long id, CreateRequestDTO createRequestDTO);
    Page<ResponseRequestDTO> getAllRequests(Pageable pageable);
    Page<RequestReportDTO> getRequestReports();
                                 }
