package com.store.api.service.impl;


import com.store.api.DTO.create.CreateRequestDTO;
import com.store.api.DTO.RequestReportDTO;
import com.store.api.DTO.response.ResponseRequestDTO;
import com.store.api.entity.Request;
import com.store.api.entity.User;
import com.store.api.mapper.RequestMapper;
import com.store.api.repository.RequestRepository;
import com.store.api.repository.UserRepository;
import com.store.api.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class RequestServiceImpl implements RequestService {
    private static final Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;


    public RequestServiceImpl(RequestRepository requestRepository, RequestMapper requestMapper, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
        this.userRepository = userRepository;
    }
    @Transactional
    @Override
    public ResponseRequestDTO createRequest(CreateRequestDTO createRequestDTO) {
        User user = userRepository.findById(createRequestDTO.userId()).
                orElseThrow(() -> new RuntimeException("User not found"));
        logger.info("در حال ایجاد درخواست جدید");
        Request request = requestMapper.toEntity(createRequestDTO);
        request.setUserId(user.getId());
        Request savedRequest = requestRepository.save(request);
        logger.info("درخواست با موفقیت ایجاد شد ");
        return requestMapper.toDTO(savedRequest);
    }

    @Override
    @Transactional
    public void deleteRequest(Long id) {
        logger.info("در حال حذف درخواست با ID: {}", id);
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("درخواست پیدا نشد"));
        logger.info("درخواست با ID: {} با موفقیت حذف شد", id);
    }

    @Override
    public Page<ResponseRequestDTO> findByNationalCode(String nationalCode, Pageable pageable) {
        logger.info("در حال جستجوی درخواست‌ها با کد ملی: {}", nationalCode);
        Page<Request> requests = requestRepository.findByNationalCode(nationalCode, pageable);
        logger.info("تعداد درخواست‌های پیدا شده: {}", requests.getTotalElements());
        return requests.map(requestMapper::toDTO);
    }
    @Override
    @Transactional
    public ResponseRequestDTO updateRequest(Long id, CreateRequestDTO createRequestDTO) {
        logger.info("در حال اپدیت درخواست با ID: {}", id);
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("درخواست پیدا نشد"));
        requestMapper.updateRequestDTO(createRequestDTO, request);
        /*
        if (createRequestDTO.firstName() != null) {
            request.setName(createRequestDTO.firstName());
        }
        if (createRequestDTO.lastName() != null) {
            request.setLastName(createRequestDTO.lastName());
        }
        if (createRequestDTO.age() != null) {
            request.setAge(createRequestDTO.age());
        }
        if (createRequestDTO.nationalCode() != null) {
            request.setNationalCode(createRequestDTO.nationalCode());
        }
        if (createRequestDTO.gender() != null) {
            request.setGender(createRequestDTO.gender());
        }
        if (createRequestDTO.number() != null) {
            request.setNumber(createRequestDTO.number());
        }
        if (createRequestDTO.typeOfBeautyService() != null) {
            request.setTypeOfBeautyService(createRequestDTO.typeOfBeautyService());
        }
        if (createRequestDTO.description() != null) {
            request.setDescription(createRequestDTO.description());
        }*/
        Request updatedRequest = requestRepository.save(request);
        logger.info("درخواست با ID: {} با موفقیت اپدیت شد", id);
        return requestMapper.toDTO(updatedRequest);
    }
    @Override
    public Page<ResponseRequestDTO> getAllRequests(Pageable pageable) {
        logger.info("در حال دریافت همه درخواست‌ها");
        Page<Request> requests = requestRepository.findAll(pageable);
        return requests.map(requestMapper::toDTO);
    }

    @Override
    public Page<RequestReportDTO> getRequestReports() {
        LocalDateTime startDate = LocalDateTime.now().minusMonths(1);
        return requestRepository.countingRequestReports(startDate , Pageable.unpaged());
    }
}