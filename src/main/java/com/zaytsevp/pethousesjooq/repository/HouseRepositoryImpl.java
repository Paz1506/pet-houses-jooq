package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Page<HouseRecord> getAll(HouseSearchArgument houseSearchArgument) {
        // TODO: Get Optional Builder
        Condition searchCondition = DSL.trueCondition();

        if (houseSearchArgument.getName() != null) {
            searchCondition = searchCondition.and(house.NAME.containsIgnoreCase(houseSearchArgument.getName()));
        }

        if (houseSearchArgument.getFilled() != null) {
            searchCondition = searchCondition.and(house.FILLED.eq(houseSearchArgument.getFilled()));
        }

        if (houseSearchArgument.getId() != null) {
            searchCondition = searchCondition.and(house.ID.eq(houseSearchArgument.getId()));
        }

        if (houseSearchArgument.getObjectSize() != null) {
            searchCondition = searchCondition.and(house.OBJECT_SIZE.eq(houseSearchArgument.getObjectSize().name()));
        }

        if (houseSearchArgument.getCapacityFrom() != null) {
            searchCondition = searchCondition.and(house.CAPACITY.greaterOrEqual(houseSearchArgument.getCapacityFrom()));
        }

        if (houseSearchArgument.getCapacityTo() != null) {
            searchCondition = searchCondition.and(house.CAPACITY.lessOrEqual(houseSearchArgument.getCapacityTo()));
        }

        List<HouseRecord> houseRecords = dsl.selectFrom(house)
                                            .where(searchCondition)
                                            .fetchInto(HouseRecord.class);

        return new PageImpl<>(houseRecords);
    }
}
