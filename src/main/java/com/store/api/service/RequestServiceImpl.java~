package com.store.api.service;


import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.RequestDTO;
import com.store.api.entity.Request;
import com.store.api.mapper.RequestMapper;
import com.store.api.repository.RequestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = Logger.getLogger(RequestServiceImpl.class.getName());
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }


    @Override
    public RequestDTO createRequest(CreateRequestDTO createRequestDTO) {
        Request request = requestMapper.toEntity(createRequestDTO);
        logger.info("Creating a new post");
        Request savedRequest = requestRepository.save(request);
        return requestMapper.toDTO(savedRequest);
    }

    @Override
    public void deleteRequest(Long id) {
        logger.info("Deleting post with id: " + id);
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        requestRepository.delete(request);
    }

    @Override
    public Page<RequestDTO> findByNationalCode(Long nationalCode, Pageable pageable) {
        Page<Request> requests = requestRepository.findByNationalCode(nationalCode, pageable);
        return requests.map(requestMapper::toDTO);
    }

    @Override
    public RequestDTO updateRequest(Long id, CreateRequestDTO createRequestDTO) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        if (createRequestDTO.name() != null) {
            request.setName(createRequestDTO.name());}
        if(createRequestDTO.lastName() != null) {
            request.setLastName(createRequestDTO.lastName());}
        if(createRequestDTO.age() != null) {
            request.setAge(createRequestDTO.age());}
        if(createRequestDTO.nationalCode() != null) {
            request.setNationalCode(createRequestDTO.nationalCode());}
        if(createRequestDTO.gender() != null) {
            request.setGender(createRequestDTO.gender());}
        if(createRequestDTO.number() != null) {
            request.setNumber(createRequestDTO.number());}
        if(createRequestDTO.typeOfBeautyService() != null) {
            request.setTypeOfBeautyService(createRequestDTO.typeOfBeautyService());}
        if(createRequestDTO.description() != null) {
            request.setDescription(createRequestDTO.description());}
        Request updatedRequest = requestRepository.save(request);
        logger.info("Updating post with id: " + id);
        return requestMapper.toDTO(updatedRequest);
    }
}
