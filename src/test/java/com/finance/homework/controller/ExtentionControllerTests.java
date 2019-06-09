package com.finance.homework.controller;

import com.finance.homework.controllers.LoanExtentionController;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ExtentionResponse;
import com.finance.homework.model.ExtentionEntity;
import com.finance.homework.services.ExtentionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtentionControllerTests {

    @InjectMocks
    LoanExtentionController loanExtentionController = new LoanExtentionController();

    @Mock
    ExtentionService extentionService;

    @Test
    public void shouldRetrieveAllExtentions() {
        List<ExtentionEntity> extentionList = makeExtentionList();
        when(extentionService.findAllExtentions()).thenReturn(extentionList);

        ResponseEntity<?> response = loanExtentionController.getAllExtentions();
        List<ExtentionResponse> extentionResponse = (List<ExtentionResponse>) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(extentionList.get(0).getId(), extentionResponse.get(0).getId());
        assertEquals(extentionList.get(2).getExtentionDays(), extentionResponse.get(2).getExtentionDays());
    }

    @Test
    public void shouldCreateNewExtention() {
        ExtentionRequest extentionRequest = newExtentionRequest();
        ExtentionEntity extentionEntity = newExtentionEntity();
        when(extentionService.createExtention(extentionRequest)).thenReturn(extentionEntity);

        ResponseEntity<?> response = loanExtentionController.createExtention(extentionRequest);
        ExtentionResponse extentionResponse = (ExtentionResponse) response.getBody();

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(extentionResponse.getId(), extentionResponse.getId());
        assertEquals(extentionResponse.getExtentionDays(), extentionResponse.getExtentionDays());
    }

    @Test
    public void shouldGetExtentionById() {
        ExtentionEntity extentionEntity = newExtentionEntity();
        when(extentionService.getExtentionById("1")).thenReturn(extentionEntity);

        ResponseEntity<?> response = loanExtentionController.getExtention("1");
        ExtentionResponse extentionResponse = (ExtentionResponse) response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(extentionResponse.getId(), extentionResponse.getId());
        assertEquals(extentionResponse.getExtentionDays(), extentionResponse.getExtentionDays());
    }

    private ExtentionRequest newExtentionRequest() {
        return ExtentionRequest.builder().loan_pk(1L).extentionDays(100).build();
    }

    private ExtentionEntity newExtentionEntity() {
        return ExtentionEntity.builder().id(1L).extentionDays(100).build();
    }

    private List<ExtentionEntity> makeExtentionList() {

        List<ExtentionEntity> extentionList = new ArrayList<>();
        extentionList.add(ExtentionEntity.builder().id(1L).extentionDays(10).build());
        extentionList.add(ExtentionEntity.builder().id(2L).extentionDays(100).build());
        extentionList.add(ExtentionEntity.builder().id(3L).extentionDays(200).build());

        return extentionList;
    }
}
