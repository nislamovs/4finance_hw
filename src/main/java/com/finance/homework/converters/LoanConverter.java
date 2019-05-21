package com.finance.homework.converters;


import com.finance.homework.domain.requests.LoanRequest;
import com.finance.homework.domain.responses.LoanResponse;
import com.finance.homework.model.LoanEntity;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class LoanConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public static LoanResponse toResponse(LoanEntity loanEntity) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        return mapper.map(loanEntity, LoanResponse.class);
    }

    public static List<LoanResponse> toResponse(List<LoanEntity> loanEntities) {
        return loanEntities.stream().map(loanEntity -> toResponse(loanEntity)).collect(Collectors.toList());
    }

    public static LoanEntity toEntity(LoanRequest loanRequest) {
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        return mapper.map(loanRequest, LoanEntity.class);
    }
}
