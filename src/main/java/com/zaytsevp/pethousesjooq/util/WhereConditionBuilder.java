package com.zaytsevp.pethousesjooq.util;

import org.jooq.Condition;
import org.jooq.impl.DSL;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.function.Function;

/**
 * Билдер для опциональных параметров предиката JOOQ
 *
 * Created by Pavel Zaytsev
 */
public class WhereConditionBuilder {

    private Condition searchCondition;

    public static WhereConditionBuilder getNew() {
        return new WhereConditionBuilder();
    }

    public WhereConditionBuilder() {
        searchCondition = DSL.trueCondition();
    }

    public WhereConditionBuilder(Condition searchCondition) {
        this.searchCondition = searchCondition;
    }

    public <FieldType> WhereConditionBuilder optionalAnd(@Nullable FieldType field, Function<FieldType, Condition> doWhenValueExists) {
        if (field != null) {
            searchCondition = searchCondition.and(doWhenValueExists.apply(field));
        }

        return this;
    }

    public WhereConditionBuilder optionalStringAnd(@Nullable String field, Function<String, Condition> doWhenValueExists) {
        if (StringUtils.isEmpty(field)) {
            searchCondition = searchCondition.and(doWhenValueExists.apply(field));
        }

        return this;
    }

    public <T extends Enum> WhereConditionBuilder optionalEnumAnd(@Nullable T field, Function<String, Condition> doWhenValueExists) {
        if (field != null) {
            searchCondition = searchCondition.and(doWhenValueExists.apply(field.name()));
        }

        return this;
    }

    public Condition build() {
        return searchCondition;
    }

}
