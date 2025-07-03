package com.store.api.service;

import com.store.api.entity.Request;
import com.store.api.mapper.RequestMapper;
import com.store.api.repository.RequestRepository;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mock;
import org.mockito.Mockito;

public class RequestServiceTest {

    @Mock
    private RequestRepository requestRepository;
    @Mock
    private RequestService requestService;
    @Mock
    private Request request;
    @Mock
    private RequestMapper requestMapper;

    @BeforeEach
    public void setUp() {

    }
}
