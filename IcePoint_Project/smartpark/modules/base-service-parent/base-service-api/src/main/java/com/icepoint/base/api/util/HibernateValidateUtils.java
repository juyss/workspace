package com.icepoint.base.api.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

public class HibernateValidateUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Deprecated
    public static String getErrors(Object obj) {
        Set<ConstraintViolation<Object>> constraintViolations = VALIDATOR.validate(obj);// 验证某个对象,，其实也可以只验证其中的某一个属性的

        for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
            return constraintViolation.getMessage();
        }
        return null;
    }

    public static boolean validate(Object obj) {
        return !VALIDATOR.validate(obj).stream().findAny().isPresent();
    }

    public static boolean validate(Object... objs) {
        for (Object obj : objs) {
            boolean result = validate(obj);
            if (!result)
                return false;
        }
        return true;
    }

    public static void validateOrThrow(Object obj) {
        // 验证某个对象,，其实也可以只验证其中的某一个属性的
        VALIDATOR.validate(obj).stream().findAny().ifPresent(violation -> {
            throw new ValidationException(violation.getMessage());
        });
    }

    public static void validateOrThrow(Object... objs) {
        for (Object obj : objs) {
            validateOrThrow(obj);
        }
    }
}
