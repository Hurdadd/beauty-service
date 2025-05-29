package com.store.api.controller;

import com.store.api.DTO.CreateRequestDTO;
import com.store.api.DTO.ResponseRequestDTO;
import com.store.api.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@Slf4j
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;
    @PostMapping
    @Operation(summary = "ایجاد یک درخواست جدید", description = "ایجاد یک درخواست جدید برای خدمات زیبایی")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست با موفقیت ایجاد شد"),
            @ApiResponse(responseCode = "400", description = "داده‌های ورودی نامعتبر است")
    })
    public ResponseEntity<ResponseRequestDTO> createRequest(
            @Parameter(description = "داده‌های درخواست برای ایجاد") @Valid @RequestBody CreateRequestDTO createRequestDTO
    ) {
        log.info("دریافت درخواست برای ایجاد درخواست جدید: {}", createRequestDTO);
        ResponseRequestDTO requestDTO = requestService.createRequest(createRequestDTO);
        log.info("درخواست با موفقیت ایجاد شد با ID: {}", createRequestDTO.id());
        return ResponseEntity.ok(requestDTO);
    }

    @GetMapping("/national-code/{nationalCode}")
    @Operation(summary = "جستجوی درخواست‌ها با کد ملی", description = "دریافت لیست درخواست‌ها بر اساس کد ملی")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست‌ها با موفقیت برگردانده شدند")
    })
    public ResponseEntity<Page<ResponseRequestDTO>> getRequestsByNationalCode(
            @Parameter(description = "کد ملی برای جستجو") @PathVariable String nationalCode,
            @Parameter(description = "شماره صفحه") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "تعداد در هر صفحه") @RequestParam(defaultValue = "10") int size
    ) {
        log.info("دریافت درخواست برای جستجوی درخواست‌ها با کد ملی: {}", nationalCode);
        Pageable pageable = PageRequest.of(page, size);
        Page<ResponseRequestDTO> requests = requestService.findByNationalCode(nationalCode, pageable);
        return ResponseEntity.ok(requests);
    }

    @GetMapping
    @Operation(summary = "دریافت همه درخواست‌ها", description = "دریافت لیست همه درخواست‌ها با صفحه‌بندی")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست‌ها با موفقیت برگردانده شدند")
    })
    public ResponseEntity<Page<ResponseRequestDTO>> getAllRequests(
            @Parameter(description = "شماره صفحه") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "تعداد در هر صفحه") @RequestParam(defaultValue = "10") int size
    ) {
        log.info("دریافت درخواست برای دریافت همه درخواست‌ها");
        Pageable pageable = PageRequest.of(page, size);
        Page<ResponseRequestDTO> requests = requestService.getAllRequests(pageable);
        return ResponseEntity.ok(requests);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "حذف یک درخواست", description = "حذف یک درخواست با استفاده از ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست با موفقیت حذف شد"),
            @ApiResponse(responseCode = "404", description = "درخواست با این ID پیدا نشد")
    })
    public ResponseEntity<Void> deleteRequest(
            @Parameter(description = "شناسه درخواست برای حذف") @PathVariable Long id
    ) {
        log.info("دریافت درخواست برای حذف درخواست با ID: {}", id);
        requestService.deleteRequest(id);
        log.info("درخواست با ID: {} با موفقیت حذف شد", id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "به‌روزرسانی یک درخواست", description = "به‌روزرسانی یک درخواست موجود با استفاده از ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "درخواست با موفقیت به‌روزرسانی شد"),
            @ApiResponse(responseCode = "404", description = "درخواست با این ID پیدا نشد")
    })
    public ResponseEntity<ResponseRequestDTO> updateRequest(
            @Parameter(description = "شناسه درخواست برای به‌روزرسانی") @PathVariable Long id,
            @Valid @RequestBody CreateRequestDTO createRequestDTO
    ) {
        log.info("دریافت درخواست برای به‌روزرسانی درخواست با ID: {}", id);
        ResponseRequestDTO updatedRequest = requestService.updateRequest(id, createRequestDTO);
        log.info("درخواست با ID: {} با موفقیت به‌روزرسانی شد", id);
        return ResponseEntity.ok(updatedRequest);
    }
}