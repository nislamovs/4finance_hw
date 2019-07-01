package com.finance.homework.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class LoanRequest {

    @ApiModelProperty(required = true, value = "User id", example = "1")
    @JsonProperty("userId")
    private Long user_pk;

    @ApiModelProperty(required = true, value = "Loan amount (EUR)", example = "100000")
    @DecimalMin(value = "100", message = "Loan amount should be min 100 and max 500000 EUR.")
    @DecimalMax(value = "500000", message = "Loan amount should be min 100 and max 500000 EUR.")
    @NotNull(message = "Loan amount cannot be empty.")
    private BigDecimal loanAmount;

    @ApiModelProperty(required = true, value = "Loan term (days)", example = "100")
    @Min(value = 1, message = "Loan term should be min 1 and max 365 days long.")
    @Max(value = 365, message = "Loan term should be min 1 and max 365 days long.")
    @NotNull(message = "Loan term cannot be empty.")
    private Integer loanTerm;

    @ApiModelProperty(required = true, hidden=true, notes = "Used to check request frequency")
    private String ipAddress;

}

