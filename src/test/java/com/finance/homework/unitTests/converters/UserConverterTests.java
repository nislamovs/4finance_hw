package com.finance.homework.unitTests.converters;

import com.finance.homework.converters.UserConverter;
import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.domain.responses.UserResponse;
import com.finance.homework.model.UserEntity;

import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.finance.homework.testHelperClasses.UserTestDataFactory.*;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserConverterTests {

    @Test
    public void shouldConvertUserRequestToUserEntity(){
        UserRequest userRequest = newUserRequest();
        UserEntity userEntity = UserConverter.toEntity(userRequest);

        assertConvertedUser(userRequest, userEntity);
    }

    @Test
    public void shouldConvertUserEntityToUserResponse(){
        UserEntity userEntity = newUserEntity();
        UserResponse userResponse = UserConverter.toResponse(userEntity);

        assertConvertedUser(userEntity, userResponse);
    }

    @Test
    public void shouldConvertUserEntityListToUserResponseList(){
        List<UserEntity> userEntities = makeUserList();
        List<UserResponse> userResponses = UserConverter.toResponse(userEntities);

        for (int n = 0; n < userEntities.size(); n++) {
            assertConvertedUser(userEntities.get(n), userResponses.get(n));
        }
    }

    @Test
    public void shouldPresetMapperOnConstruct(){
        UserConverter userConv = new UserConverter();
        ModelMapper testMapper = (ModelMapper) ReflectionTestUtils.getField(userConv,"mapper");
        assertTrue(testMapper.getConfiguration().isFieldMatchingEnabled());
    }

    private void assertConvertedUser(UserRequest userRequest, UserEntity userEntity) {
        assertEquals(userRequest.getFirstname(),  userEntity.getFirstname());
        assertEquals(userRequest.getLastname(),   userEntity.getLastname());
        assertEquals(userRequest.getEmail(),      userEntity.getEmail());
        assertEquals(userRequest.getAddress(),    userEntity.getAddress());
        assertEquals(userRequest.getPhone(),      userEntity.getPhone());
    }

    private void assertConvertedUser(UserEntity userEntity, UserResponse userResponse) {
        assertEquals(userEntity.getId(),  userResponse.getId());
        assertEquals(userEntity.getFirstname(),  userResponse.getFirstname());
        assertEquals(userEntity.getLastname(),   userResponse.getLastname());
        assertEquals(userEntity.getEmail(),      userResponse.getEmail());
        assertEquals(userEntity.getAddress(),    userResponse.getAddress());
        assertEquals(userEntity.getPhone(),      userResponse.getPhone());
    }
}
