package com.finance.homework.converter;

import com.finance.homework.converters.ExtentionConverter;
import com.finance.homework.converters.UserConverter;
import com.finance.homework.domain.requests.ExtentionRequest;
import com.finance.homework.domain.responses.ExtentionResponse;
import com.finance.homework.model.ExtentionEntity;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.makeExtentionList;
import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.newExtentionEntity;
import static com.finance.homework.testHelperClasses.ExtentionTestDataFactory.newExtentionRequest;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExtentionConverterTests {

    @Test
    public void shouldConvertExtentionRequestToExtentionEntity(){
        ExtentionRequest extentionRequest = newExtentionRequest();
        ExtentionEntity extentionEntity = ExtentionConverter.toEntity(extentionRequest);

        assertConvertedExtention(extentionRequest, extentionEntity);
    }

    @Test
    public void shouldConvertExtentionEntityToExtentionResponse(){
        ExtentionEntity extentionEntity = newExtentionEntity();
        ExtentionResponse extentionResponse = ExtentionConverter.toResponse(extentionEntity);

        assertConvertedExtention(extentionEntity, extentionResponse);
    }

    @Test
    public void shouldConvertExtentionEntityListToExtentionResponseList(){
        List<ExtentionEntity> extentionEntities = makeExtentionList();
        List<ExtentionResponse> extentionResponses = ExtentionConverter.toResponse(extentionEntities);

        for (int n = 0; n < extentionEntities.size(); n++) {
            assertConvertedExtention(extentionEntities.get(n), extentionResponses.get(n));
        }
    }

    @Test
    public void shouldPresetMapperOnConstruct(){
        ExtentionConverter extentionConverter = new ExtentionConverter();
        ModelMapper testMapper = (ModelMapper) ReflectionTestUtils.getField(extentionConverter,"mapper");
        assertTrue(testMapper.getConfiguration().isFieldMatchingEnabled());
    }

    private void assertConvertedExtention(ExtentionRequest extentionRequest, ExtentionEntity extentionEntity) {
        assertEquals(extentionRequest.getExtentionDays(),    extentionEntity.getExtentionDays());
    }

    private void assertConvertedExtention(ExtentionEntity extentionEntity, ExtentionResponse extentionResponse) {
        assertEquals(extentionEntity.getId(),                extentionResponse.getId());
        assertEquals(extentionEntity.getExtentionDays(),     extentionResponse.getExtentionDays());
    }
}
