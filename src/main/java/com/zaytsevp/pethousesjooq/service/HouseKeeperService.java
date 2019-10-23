package com.zaytsevp.petHouseKeepersjooq.service;


import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
public interface HouseKeeperService {

    HouseKeeperRecord create(HouseKeeperCreateArgument HouseKeeperCreateArgument);

    Optional<HouseKeeperRecord> getById(String id);

    HouseKeeperRecord getExistingById(String id);

    List<HouseKeeperRecord> getAll(HouseKeeperSearchArgument searchArgument, Pageable pageable);
}
