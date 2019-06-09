package com.finance.homework.converters;


import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.domain.responses.UserResponse;
import com.finance.homework.model.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public UserConverter() {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
    }

    public static UserResponse toResponse(UserEntity userEntity) {
        return mapper.map(userEntity, UserResponse.class);
    }

    public static List<UserResponse> toResponse(List<UserEntity> userEntities) {
        return userEntities.stream().map(userEntity -> toResponse(userEntity)).collect(Collectors.toList());
    }

    public static UserEntity toEntity(UserRequest userRequest) {
        return mapper.map(userRequest, UserEntity.class);
    }
}
