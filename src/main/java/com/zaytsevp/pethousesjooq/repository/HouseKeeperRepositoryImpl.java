package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.enums.EntityStatus;
import com.zaytsevp.pethousesjooq.model.tables.HouseKeeper;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
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
public class HouseKeeperRepositoryImpl implements HouseKeeperRepository, BaseJOOQRepository<HouseKeeperRecord> {

    private final DSLContext dsl;

    private final HouseKeeper houseKeeper = HouseKeeper.HOUSE_KEEPER;

    @Autowired
    public HouseKeeperRepositoryImpl(DSLContext dsl) {this.dsl = dsl;}

    @Override
    public HouseKeeperRecord create(HouseKeeperCreateArgument houseKeeperCreateArgument) {
        return dsl.insertInto(houseKeeper)
                  .set(houseKeeper.ID, UUID.randomUUID().toString())
                  .set(houseKeeper.FIRST_NAME, houseKeeperCreateArgument.getFirstName())
                  .set(houseKeeper.LAST_NAME, houseKeeperCreateArgument.getLastName())
                  .set(houseKeeper.AGE, houseKeeperCreateArgument.getAge())
                  .set(houseKeeper.LEVEL, houseKeeperCreateArgument.getLevel())
                  .set(houseKeeper.STATUS, EntityStatus.ACTIVE.name())
                  .returning()
                  .fetchOne();
    }

    @Override
    public Optional<HouseKeeperRecord> getById(String id) {
        return dsl.select()
                  .from(houseKeeper)
                  .where(houseKeeper.ID.equal(id))
                  .fetchOptionalInto(HouseKeeperRecord.class);
    }

    @Override
    public Page<HouseKeeperRecord> getAll(HouseKeeperSearchArgument houseKeeperSearchArgument, Pageable pageable) {
        List<HouseKeeperRecord> houseKeeperRecords = dsl.selectFrom(houseKeeper)
                                                        .where(WhereConditionBuilder.getNew()
                                                                                    .optionalStringAnd(houseKeeperSearchArgument.getFirstName(), houseKeeper.FIRST_NAME::containsIgnoreCase)
                                                                                    .optionalStringAnd(houseKeeperSearchArgument.getLastName(), houseKeeper.LAST_NAME::containsIgnoreCase)
                                                                                    .optionalAnd(houseKeeperSearchArgument.getAgeFrom(), houseKeeper.AGE::greaterOrEqual)
                                                                                    .optionalAnd(houseKeeperSearchArgument.getAgeTo(), houseKeeper.AGE::lessOrEqual)
                                                                                    .optionalAnd(houseKeeperSearchArgument.getLevelFrom(), houseKeeper.LEVEL::greaterOrEqual)
                                                                                    .optionalAnd(houseKeeperSearchArgument.getLevelTo(), houseKeeper.LEVEL::lessOrEqual)
                                                                                    .optionalEnumAnd(houseKeeperSearchArgument.getStatus(), houseKeeper.STATUS::eq)
                                                                                    .optionalStringAnd(houseKeeperSearchArgument.getId(), houseKeeper.ID::eq)
                                                                                    .build())
                                                        .orderBy(getSortFields(pageable.getSort(), houseKeeper))
                                                        .limit(pageable.getPageSize())
                                                        .offset(new Long(pageable.getOffset()).intValue())
                                                        .fetchInto(HouseKeeperRecord.class);

        return new PageImpl<>(houseKeeperRecords);
    }
}
