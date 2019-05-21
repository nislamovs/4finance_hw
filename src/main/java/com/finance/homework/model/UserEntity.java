package com.finance.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="users")
@Table(name="users")
public class UserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name="firstname", updatable = false, nullable=false)
    private String firstname;

    @Column(name="lastname", updatable = false, nullable=false)
    private String lastname;

    @Column(name="address", updatable = false, nullable=false)
    private String address;

    @Column(name="email", updatable = false, nullable=false)
    private String email;

    @Column(name="phone", updatable = false, nullable=false)
    private String phone;

    @Column(name="is_blocked", updatable = true, nullable=false)
    private boolean isBlocked;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private List<LoanEntity> loans;

}
