package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import com.zaytsevp.pethousesjooq.util.WhereConditionBuilder;
import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.TableField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Pavel Zaytsev
 */
@Repository
public class HouseRepositoryImpl implements HouseRepository {

    private final DSLContext dsl;

    private final House house = House.HOUSE;

    @Autowired
    public HouseRepositoryImpl(DSLContext dsl) {this.dsl = dsl;}

    @Override
    public HouseRecord create(int capacity, boolean filled, String name, String objectSize) {
        return dsl.insertInto(house)
                  .set(house.ID, UUID.randomUUID().toString())
                  .set(house.CAPACITY, capacity)
                  .set(house.FILLED, filled)
                  .set(house.NAME, name)
                  .set(house.OBJECT_SIZE, objectSize)
                  .returning()
                  .fetchOne();
    }

    @Override
    public Optional<HouseRecord> getById(String id) {
        return dsl.select()
                  .from(house)
                  .where(house.ID.equal(id))
                  .fetchOptionalInto(HouseRecord.class);
    }

    @Override
    public Page<HouseRecord> getAll(HouseSearchArgument houseSearchArgument, Pageable pageable) {
        List<HouseRecord> houseRecords = dsl.selectFrom(house)
                                            .where(WhereConditionBuilder.getNew()
                                                                        .optionalStringAnd(houseSearchArgument.getName(), house.NAME::containsIgnoreCase)
                                                                        .optionalAnd(houseSearchArgument.getFilled(), house.FILLED::eq)
                                                                        .optionalStringAnd(houseSearchArgument.getId(), house.ID::eq)
                                                                        .optionalEnumAnd(houseSearchArgument.getObjectSize(), house.OBJECT_SIZE::eq)
                                                                        .optionalAnd(houseSearchArgument.getCapacityFrom(), house.CAPACITY::greaterOrEqual)
                                                                        .optionalAnd(houseSearchArgument.getCapacityTo(), house.CAPACITY::lessOrEqual)
                                                                        .build())
                                            .orderBy(getSortFields(pageable.getSort()))
                                            .limit(pageable.getPageSize())
                                            .offset(new Long(pageable.getOffset()).intValue())
                                            .fetchInto(HouseRecord.class);

        return new PageImpl<>(houseRecords);
    }

    /** получить поле мета класса JOOQ из строкового названия поля */
    private TableField getTableField(String sortFieldName) {
        TableField sortField = null;

        try {
            // UpperCase (JOOQ field declaration specific)
            Field tableField = house.getClass().getField(sortFieldName.toUpperCase());
            sortField = (TableField) tableField.get(house);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            String errorMessage = String.format("Could not find table field: %s", sortFieldName);

            throw new InvalidDataAccessApiUsageException(errorMessage, e);
        }

        return sortField;
    }

    /** применить направление сортировки поля и вернуть поле сортировки */
    private SortField<?> convertTableFieldToSortField(TableField tableField, Sort.Direction sortDirection) {
        return sortDirection.isDescending() ? tableField.desc()
                                            : tableField.asc();
    }

    /** получить поля сортировки */
    private Collection<SortField<?>> getSortFields(Sort sortSpecification) {
        Collection<SortField<?>> result = new ArrayList<>();

        if (sortSpecification == null) {
            return result;
        }

        for (Sort.Order specifiedField : sortSpecification) {
            String fieldName = specifiedField.getProperty();
            Sort.Direction direction = specifiedField.getDirection();

            TableField tableField = getTableField(fieldName);
            SortField<?> sortField = convertTableFieldToSortField(tableField, direction);

            result.add(sortField);
        }

        return result;
    }
}
