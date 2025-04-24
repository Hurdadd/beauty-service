package com.store.api.controller;

import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.RequestDTO;
import com.store.api.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@Tag(name = "Request API", description = "API برای مدیریت درخواست‌ها در خدمات زیبایی")
public class RequestController {
    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @Operation(summary = "ایجاد یک درخواست جدید", description = "ایجاد یک درخواست جدید برای خدمات زیبایی")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "درخواست با موفقیت ایجاد شد"),
            @ApiResponse(responseCode = "400", description = "داده‌های ورودی نامعتبر است")
    })
    public ResponseEntity<RequestDTO> createRequest(
            @Valid @RequestBody CreateRequestDTO createRequestDTO
    ) {
        logger.info("دریافت درخواست برای ایجاد یک درخواست جدید با کد ملی: {}", createRequestDTO.nationalCode());
        RequestDTO requestDTO = requestService.createRequest(createRequestDTO);
        logger.info("درخواست با موفقیت ایجاد شد، ID: {}", createRequestDTO.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(requestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "اپدیت یک درخواست", description = "اپدیت یک درخواست  با استفاده از ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست با موفقیت به‌روزرسانی شد"),
            @ApiResponse(responseCode = "400", description = "داده‌های ورودی نامعتبر است"),
            @ApiResponse(responseCode = "404", description = "درخواست با این ID پیدا نشد")
    })
    public ResponseEntity<RequestDTO> updateRequest(
            @Parameter(description = "ایدی درخواست برای اپدیت") @PathVariable Long id,
            @Valid @RequestBody CreateRequestDTO createRequestDTO
    ) {
        logger.info("دریافت درخواست برای اپدیت درخواست با ID: {}", id);
        RequestDTO requestDTO = requestService.updateRequest(id, createRequestDTO);
        logger.info("درخواست با ID: {} با موفقیت اپدیت شد", id);
        return ResponseEntity.ok(requestDTO);
    }

    @GetMapping("/national-code/{nationalCode}")
    @Operation(summary = "جستجو بر اساس کد ملی", description = "دریافت لیست درخواست‌ها بر اساس کد ملی با صفحه‌بندی")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "لیست درخواست‌ها با موفقیت برگردانده شد")
    })
    public ResponseEntity<Page<RequestDTO>> findByNationalCode(
            @Parameter(description = "کد ملی برای جستجو") @PathVariable Long nationalCode,
            @Parameter(description = "تنظیمات صفحه‌بندی (page, size, sort)") Pageable pageable
    ) {
        logger.info("دریافت درخواست برای جستجوی درخواست‌ها با کد ملی: {}", nationalCode);
        Page<RequestDTO> requests = requestService.findByNationalCode(nationalCode, pageable);
        logger.info("تعداد درخواست‌های پیدا شده: {}", requests.getTotalElements());
        return ResponseEntity.ok(requests);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف یک درخواست", description = "حذف یک درخواست با استفاده از ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "درخواست با موفقیت حذف شد"),
            @ApiResponse(responseCode = "404", description = "درخواست با این ID پیدا نشد")
    })
    public ResponseEntity<Void> deleteRequest(
            @Parameter(description = "شناسه درخواست برای حذف") @PathVariable Long id
    ) {
        logger.info("دریافت درخواست برای حذف درخواست با ID: {}", id);
        requestService.deleteRequest(id);
        logger.info("درخواست با ID: {} با موفقیت حذف شد", id);
        return ResponseEntity.noContent().build();
    }
}