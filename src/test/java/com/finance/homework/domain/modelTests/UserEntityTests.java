package com.finance.homework.domain.modelTests;

import com.finance.homework.model.LoanEntity;
import com.finance.homework.model.UserEntity;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.finance.homework.testHelperClasses.AuxTestMethodsFactory.hasFieldsWithNullValues;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEntityTests {

    @Test
    public void shouldMatchObjectValuesAndStringContent() {

        UserEntity userEntity = new UserEntity();
        assertThat(userEntity, notNullValue());
        assertTrue(hasFieldsWithNullValues(userEntity, Arrays.asList("firstname")));
    }

    @Test
    public void shouldBeEqual() {
        UserEntity userEntity1 = presetUserEntity_Manual();
        UserEntity userEntity2 = presetUserEntity_Manual();

        assertTrue(userEntity1.equals(userEntity2));

        userEntity1 = userEntity2;
        assertEquals(userEntity1.hashCode(), userEntity2.hashCode());
        assertEquals((new UserEntity()).hashCode(), (new UserEntity()).hashCode());

        userEntity1 = presetUserEntity_Builder();
        assertEquals(userEntity1.hashCode(), userEntity2.hashCode());
        assertEquals((new UserEntity()).hashCode(), (new UserEntity()).hashCode());
//
//        userEntity1 = presetUserEntity_AllArgsConstructor();
//        assertEquals(userEntity1.hashCode(), userEntity2.hashCode());
//        assertEquals((new UserEntity()).hashCode(), (new UserEntity()).hashCode());

    }

    private UserEntity presetUserEntity_Manual() {
        UserEntity userEntity = new UserEntity();
        List<LoanEntity> loans = new ArrayList<>(Arrays.asList(new LoanEntity()));

        userEntity.setId(1L);
        userEntity.setFirstname("JohnTest");
        userEntity.setLastname("DoeTest");
        userEntity.setAddress("test street 1");
        userEntity.setBlocked(false);
        userEntity.setEmail("asdasd@sdfsdfsdf.ll");
        userEntity.setPhone("365463745764");
        userEntity.setLoans(loans);

        return userEntity;
    }

    private UserEntity presetUserEntity_Builder() {
        UserEntity userEntity = UserEntity.builder().id(1L).firstname("JohnTest").lastname("DoeTest").address("test street 1")
                .address("test street 1").isBlocked(false).email("asdasd@sdfsdfsdf.ll").phone("365463745764")
                .loans(new ArrayList<>(Arrays.asList(new LoanEntity()))).build();


        return userEntity;
    }

//    private UserEntity presetUserEntity_AllArgsConstructor() {
//        List<LoanEntity> loans = new ArrayList<>(Arrays.asList(new LoanEntity()));
//
//        return new UserEntity(1L, "JohnTest", "DoeTest", "test street 1", "asdasd@sdfsdfsdf.ll", "365463745764", false, loans);
//    }
}
