package com.finance.homework.domain.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum LoanStatus {

    PENDING(1, "pending"),
    APPROVED(2, "approved"),
    REJECTED(3, "rejected"),
    PAYED_OFF(4, "payed_off"),
    SENT_TO_COLLECTION(5, "sent_to_collection"),
    MANUAL_CHECK(6, "manual_check");

    @JsonValue
    @Getter private Integer id;

    @JsonValue
    @Getter private String status;
}
