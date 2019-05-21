package com.finance.homework.domain.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
//@ApiModel
public enum LoanStatus {

    PENDING(1, "pending"),
    APPROVED(2, "approved"),
    REJECTED(3, "rejected"),
    PAYED_OFF(4, "payed_off"),
    SENT_TO_COLLECTION(5, "sent_to_collection"),
    MANUAL_CHECK(6, "manual_check");

    @JsonValue
    @Getter @Setter private Integer id;

    @JsonValue
//    @ApiModelProperty(allowableValues = "pending, approved, rejected, payed_off, sent_to_collection")
    @Getter @Setter private String status;
}
