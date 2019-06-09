package com.finance.homework.domain.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ApiModel
public class UserResponse {

    @ApiModelProperty(required = true, value = "User id", example = "1")
    private Long id;

    @ApiModelProperty(required = true, value = "User name", example = "John")
    private String firstname;

    @ApiModelProperty(required = true, value = "User surname", example = "Dillinger")
    private String lastname;

    @ApiModelProperty(required = true, value = "User access ", example = "Wall street 12")
    private String address;

    @ApiModelProperty(required = true, value = "User email", example = "user123@gmail.com")
    private String email;

    @ApiModelProperty(required = true, value = "User phone", example = "3456783489")
    private String phone;

    @ApiModelProperty(required = true, value = "User status", example = "true", notes = "User status (blocked or not)")
    private boolean isBlocked;

    @ApiModelProperty(required = false, value = "User loans")
    @JsonInclude(NON_NULL)
    private List<LoanResponse> loans;

    @ApiModelProperty(required = true, value = "Creation date of current user")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

}
