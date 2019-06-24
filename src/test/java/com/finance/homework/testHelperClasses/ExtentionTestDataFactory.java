package com.finance.homework.testHelperClasses;

import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.model.ExtentionEntity;

import java.util.ArrayList;
import java.util.List;

public class ExtentionTestDataFactory {

    public static ExtentionRequest newExtentionRequest() {
        return ExtentionRequest.builder().loan_pk(1L).extentionDays(100).build();
    }

    public static ExtentionEntity newExtentionEntity() {
        return ExtentionEntity.builder().id(1L).extentionDays(100).build();
    }

    public static List<ExtentionEntity> makeExtentionList() {

        List<ExtentionEntity> extentionList = new ArrayList<>();
        extentionList.add(ExtentionEntity.builder().id(1L).extentionDays(10).build());
        extentionList.add(ExtentionEntity.builder().id(2L).extentionDays(100).build());
        extentionList.add(ExtentionEntity.builder().id(3L).extentionDays(200).build());

        return extentionList;
    }
}
