package com.zaytsevp.pethousesjooq.service;

import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.projections.HouseWithKeeperProjection;
import com.zaytsevp.pethousesjooq.service.argument.HouseCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
public interface HouseService {

    HouseRecord create(HouseCreateArgument houseCreateArgument);

    Optional<HouseRecord> getById(String id);

    Optional<HouseWithKeeperProjection> getProjectionWithKeeperById(String id);

    HouseRecord getExistingById(String id);

    HouseWithKeeperProjection getExistingProjectionWithKeeperById(String id);

    List<HouseRecord> getAll(HouseSearchArgument searchArgument, Pageable pageable);
}
