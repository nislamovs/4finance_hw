package com.finance.homework.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class ExtentionRequest implements Serializable {

    @ApiModelProperty(required = true, value = "Loan id", example = "1")
    @JsonProperty("loanId")
    private Long loan_pk;

    @ApiModelProperty(required = true, value = "Loan extention term (days)", example = "3")
    @Min(value = 1, message = "Loan extention should be min 1 and max 365 days long.")
    @Max(value = 365, message = "Loan extention should be min 1 and max 365 days long.")
    @NotNull(message = "Extention term cannot be empty.")
    private Integer extentionDays;

}

