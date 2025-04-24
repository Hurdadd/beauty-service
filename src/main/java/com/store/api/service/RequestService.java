package com.store.api.service;

import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.RequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface RequestService {
    RequestDTO createRequest(CreateRequestDTO createRequestDTO);
    void deleteRequest(Long id);
    Page<RequestDTO> findByNationalCode(Long nationalCode, Pageable pageable);
    RequestDTO updateRequest(Long id, CreateRequestDTO createRequestDTO);
                                 }
