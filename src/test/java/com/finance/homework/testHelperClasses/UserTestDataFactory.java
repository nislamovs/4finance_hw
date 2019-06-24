package com.finance.homework.testHelperClasses;

import com.finance.homework.domain.requests.UserRequest;
import com.finance.homework.model.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class UserTestDataFactory {

    public static UserRequest newUserRequest() {
        return UserRequest.builder().firstname("JohnTest").lastname("DoeTest").address("Paper street 1")
                .email("john.doe@test.com").phone("076455735735").build();
    }

    public static UserEntity newUserEntity() {
        return UserEntity.builder().id(1L).firstname("JohnTest").lastname("DoeTest").address("Paper street 1")
                .email("john.doe@test.com").phone("076455735735").build();
    }

    public static List<UserEntity> makeUserList() {

        List<UserEntity> userList = new ArrayList<>();
        userList.add(UserEntity.builder().id(1L).firstname("JohnTest").lastname("CollinsTest").address("Paper street 1")
                .email("john.collins@test1.com").phone("076455735735").build());

        userList.add(UserEntity.builder().id(2L).firstname("DavidTest").lastname("DoeTest").address("Paper street 2")
                .email("david.doe@test2.com").phone("7456747456").build());

        userList.add(UserEntity.builder().id(3L).firstname("AlanTest").lastname("FisherTest").address("Paper street 3")
                .email("alan.fisher@test3.com").phone("677858785").build());

        return userList;
    }
}
