package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
@NoRepositoryBean
public interface HouseKeeperRepository {

    HouseKeeperRecord create(HouseKeeperCreateArgument houseKeeperCreateArgument);

    Optional<HouseKeeperRecord> getById(String id);

    Page<HouseKeeperRecord> getAll(HouseKeeperSearchArgument houseKeeperSearchArgument, Pageable pageable);
}
