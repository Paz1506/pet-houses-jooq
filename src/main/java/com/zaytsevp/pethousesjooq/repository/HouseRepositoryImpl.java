package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.House;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
