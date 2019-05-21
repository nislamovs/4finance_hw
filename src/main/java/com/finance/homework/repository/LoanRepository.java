package com.finance.homework.repository;

import com.finance.homework.domain.enums.LoanStatus;
import com.finance.homework.model.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsLoanEntityByIdAndStatus(Long loanId, LoanStatus loanStatus);

    boolean existsLoanEntityById(Long loanId);

    @Modifying
    @Query(value = "update loans l set l.status = UPPER(:loanStatus) " +
            "WHERE CREATED_DATE   > DATEADD('DAY',0, CURRENT_DATE) " +
            "and  loan_amount >= :loanAmount", nativeQuery = true)
    int processBigLoans(@Param("loanStatus") String loanStatus, @Param("loanAmount") BigDecimal loanAmount);


    @Query(value = "select * from loans " +
            "WHERE CREATED_DATE   > DATEADD('DAY',0, CURRENT_DATE) " +
            "and  status = UPPER('PENDING') " +
            "and  loan_amount < :maxAmount " , nativeQuery = true)
    List<LoanEntity> getUnprocessedLoans(@Param("maxAmount") BigDecimal maxAmount);

}
