package com.finance.homework.testHelperClasses;

import org.springframework.test.util.ReflectionTestUtils;
import java.util.List;
import java.util.Optional;

public class AuxTestMethodsFactory {

    public static boolean hasFieldsWithNullValues(Object obj, List<String> includedFields) {
        if (obj == null)
            return true;

        Optional<String> result = includedFields.stream().filter(field -> ReflectionTestUtils.getField(obj, field) == null).findAny();

        return result.isPresent();
    }
}
