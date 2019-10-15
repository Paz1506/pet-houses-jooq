package com.zaytsevp.pethousesjooq.util;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

/** Простой валидатор */
public class Validator {

    /** Валидация строкового параметра */
    public static void validateStringParam(String param, String error) {
        if (!StringUtils.hasText(param)) throw new IllegalArgumentException(error);
    }

    /** Валидация коллекции */
    public static void validateCollectionParam(Collection param, String error) {
        if (CollectionUtils.isEmpty(param)) throw new IllegalArgumentException(error);
    }

    /** Проверка на null */
    public static void validateObjectParam(Object param, String error) {
        if (param == null) throw new IllegalArgumentException(error);
    }

    /** Валидация по условию */
    public static void validateByCondition(boolean condition, String error) {
        if (!condition) throw new IllegalArgumentException(error);
    }
}
