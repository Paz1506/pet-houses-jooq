package com.zaytsevp.pethousesjooq.repository;

import org.jooq.Record;
import org.jooq.SortField;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Base repository for JOOQ ORM with paging and sorting support
 *
 * Created by Pavel Zaytsev
 */
public interface BaseJOOQRepository<TableClass extends Record> {

    /** получить поле мета класса JOOQ из строкового названия поля */
    default TableField getTableField(String sortFieldName, TableImpl<TableClass> tableClassInstance) {
        TableField sortField;

        try {
            // UpperCase (JOOQ field declaration specific)
            Field tableField = tableClassInstance.getClass().getField(sortFieldName.toUpperCase());
            sortField = (TableField) tableField.get(tableClassInstance);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            String errorMessage = String.format("Could not find table field: %s", sortFieldName);

            throw new InvalidDataAccessApiUsageException(errorMessage, e);
        }

        return sortField;
    }

    /** применить направление сортировки поля и вернуть поле сортировки */
    default SortField<?> convertTableFieldToSortField(TableField tableField, Sort.Direction sortDirection) {
        return sortDirection.isDescending() ? tableField.desc()
                                            : tableField.asc();
    }

    /** получить поля сортировки */
    default Collection<SortField<?>> getSortFields(Sort sortSpecification, TableImpl<TableClass> tableClassInstance) {
        Collection<SortField<?>> result = new ArrayList<>();

        if (sortSpecification == null) {
            return result;
        }

        for (Sort.Order specifiedField : sortSpecification) {
            String fieldName = specifiedField.getProperty();
            Sort.Direction direction = specifiedField.getDirection();

            TableField tableField = getTableField(fieldName, tableClassInstance);
            SortField<?> sortField = convertTableFieldToSortField(tableField, direction);

            result.add(sortField);
        }

        return result;
    }
}
