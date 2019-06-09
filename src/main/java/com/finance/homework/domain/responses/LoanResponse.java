package com.finance.homework.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
public class LoanResponse {

    @ApiModelProperty(required = true, value = "Loan Id")
    private Long id;

    @ApiModelProperty(required = true, value = "Loan amount")
    private BigDecimal loanAmount;

    @ApiModelProperty(required = true, value = "Loan term")
    private Integer loanTerm;

    @ApiModelProperty(required = true, value = "Loan status")
    private String status;

    @ApiModelProperty(required = true, value = "User total debt")
    private BigDecimal debt;

    @ApiModelProperty(required = false, value = "Loan extentions array")
    @JsonInclude(NON_NULL)
    private List<ExtentionResponse> loanExtentions;

    @ApiModelProperty(required = true, value = "Creation date of current user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @ApiModelProperty(required = true, value = "Modification date of current user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime modifiedDate;

}
