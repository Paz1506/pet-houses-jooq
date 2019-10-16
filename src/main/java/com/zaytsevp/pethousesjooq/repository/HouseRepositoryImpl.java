package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import com.zaytsevp.pethousesjooq.util.WhereConditionBuilder;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
                                            .orderBy(house.CAPACITY.desc()) // FIXME: remove hardcode
                                            .limit(pageable.getPageSize())
                                            .offset(new Long(pageable.getOffset()).intValue())
                                            .fetchInto(HouseRecord.class);

        return new PageImpl<>(houseRecords);
    }
}
