package com.finance.homework.repository;

import com.finance.homework.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsUserEntityByEmail(String email);

    boolean existsUserEntityById(Long id);

//    @Modifying
//    @Query("UPDATE users u SET u.isBlocked = 'true' WHERE u.id IN :userIds")
//    int blockUsers(@Param("userIds") List<Long> userIds);

    @Modifying
    @Query(value = "update users u set u.is_blocked='true'" +
                    "where u.id in (" +
                        " SELECT user_pk as id from LOANS l" +
                        " WHERE CREATED_DATE >= SYSDATE - 1 GROUP BY id having count(l.ip_address) >= :attempts" +
                    ")", nativeQuery = true)
    int blockUsersWithTooManyLoanApplicationAttempts(@Param("attempts") int attempts);

}
