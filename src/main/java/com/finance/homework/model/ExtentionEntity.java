package com.finance.homework.model;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "extentions")
@Table(name = "extentions")
public class ExtentionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="extention_period", nullable=false)
    private Integer extentionDays;

    @ManyToOne
    @JoinColumn(name = "loan_pk")
    private LoanEntity loanEntity;

}