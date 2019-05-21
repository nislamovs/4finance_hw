package com.finance.homework.model;

import com.finance.homework.domain.enums.LoanStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="loans")
@Table(name="loans")
public class LoanEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="loan_amount", nullable=false)
    private BigDecimal loanAmount;

    @Column(name="debt", columnDefinition = "0")
    private BigDecimal debt;

    @Column(name="loan_term", nullable=false)
    private Integer loanTerm;

    @Column(name="ip_address", nullable=false)
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable=false)
    private LoanStatus status;

    @OneToMany(mappedBy = "loanEntity", cascade = CascadeType.ALL)
    private List<ExtentionEntity> extentions;

    @ManyToOne
    @JoinColumn(name = "user_pk")
    private UserEntity userEntity;

//    @ManyToOne
//    @JoinColumn(name = "user_pk")
//    private Long user_pk;

//    @Column(name="user_pk", nullable=false)
//    private Long user_pk;

}
