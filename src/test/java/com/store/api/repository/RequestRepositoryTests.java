package com.store.api.repository;

import com.store.api.DTO.RequestReportDTO;
import com.store.api.entity.Request;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.store.api.enums.Gender.MALE;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@DataJpaTest
public class RequestRepositoryTests {

    @Autowired
    private RequestRepository requestRepository;

    private Request request1;


    @BeforeEach
    void setup() {
        // given
        request1 = new Request();
        request1.setName("Test");
        request1.setLastName("Test");
        request1.setDescription("Test");
        request1.setAge(18);
        request1.setNumber("09102591531");
        request1.setGender(MALE);
        request1.setNationalCode("09110999");
        request1.setTypeOfBeautyService("Test1");
    }

    @DisplayName("Testing Create Request")
    @Test
    public void givenRequestObject_whenSave_thenReturnSavedRequest() {
        // when - عملیات ذخیره را انجام می‌دهیم
        Request savedRequest = requestRepository.save(request1);

        // then - نتیجه را بررسی می‌کنیم
        assertThat(savedRequest).isNotNull();
        assertThat(savedRequest.getId()).isGreaterThan(0);
    }

    @DisplayName("Testing Get All Requests")
    @Test
    public void givenRequestsList_whenFindAll_thenReturnRequestsList() {
        // given - یک درخواست دیگر برای این تست خاص اضافه می‌کنیم
        Request request2 = new Request();
        request2.setName("Test2");
        request2.setLastName("Test2");
        request2.setNationalCode("091210999");
        request2.setTypeOfBeautyService("Test2");

        requestRepository.save(request1);
        requestRepository.save(request2);

        // when
        List<Request> requests = requestRepository.findAll();

        // then
        assertThat(requests).isNotNull();
        assertThat(requests.size()).isEqualTo(2);
    }

    @DisplayName("Testing Update Request")
    @Test
    public void givenRequestObject_whenUpdateRequest_thenUpdatedRequest() {
        // given
        requestRepository.save(request1);

        // when
        Request savedRequest = requestRepository.findById(request1.getId()).get();
        savedRequest.setAge(19);
        savedRequest.setLastName("Test2");
        Request updatedRequest = requestRepository.save(savedRequest);

        // then
        assertThat(updatedRequest.getLastName()).isEqualTo("Test2");
        assertThat(updatedRequest.getAge()).isEqualTo(19);
    }

    @DisplayName("Testing Delete Request")
    @Test
    public void givenRequestObject_whenDeleteRequest_thenRequestDeleted() {
        // given
        requestRepository.save(request1);

        // when
        requestRepository.deleteById(request1.getId());
        Optional<Request> deletedRequest = requestRepository.findById(request1.getId());

        // then
        assertThat(deletedRequest).isEmpty();
    }

    @DisplayName("Testing Find By NationalCode Request")
    @Test
    public void givenNationalCode_whenFindByNationalCode_thenRequestFound() {
        // given
        requestRepository.save(request1);

        // when
        Page<Request> savedRequestPage = requestRepository.findByNationalCode("09110999", Pageable.unpaged());

        // then
        assertThat(savedRequestPage.getTotalElements()).isEqualTo(1);
        assertThat(savedRequestPage.getContent().get(0).getNationalCode()).isEqualTo("09110999");
    }


    @DisplayName("Testing Get Report Requests")
    @Test
    public void givenRequestReport_whenFindReport_thenRequestReport() {
        // given
        Request request2 = new Request();
        request2.setName("Test2");
        request2.setLastName("Test2");
        request2.setGender(MALE);
        request2.setAge(18);
        request2.setNumber("09102591531");
        request2.setNationalCode("091210999");
        request2.setTypeOfBeautyService("Test2");

        Request request3 = new Request();
        request3.setName("Test2");
        request3.setLastName("Test2");
        request2.setGender(MALE);
        request2.setAge(18);
        request2.setNumber("09102591531");
        request3.setNationalCode("091310999");
        request3.setTypeOfBeautyService("Test2");
        requestRepository.save(request1);
        requestRepository.save(request2);
        requestRepository.save(request3);

        // when
        Page<RequestReportDTO> results = requestRepository.countingRequestReports(LocalDateTime.now().minusDays(1) , Pageable.unpaged());

        Map<String, Long> resultMap = results.stream()
                .collect(Collectors.toMap(
                        RequestReportDTO::typeOfBeautyService,
                        RequestReportDTO::count
                ));

        // then
        assertThat(resultMap)
                .hasSize(2)
                .containsEntry("Test1", 1L)
                .containsEntry("Test2", 2L);
    }
}