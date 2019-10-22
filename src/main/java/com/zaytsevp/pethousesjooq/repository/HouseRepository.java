package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
@NoRepositoryBean
public interface HouseRepository {

    HouseRecord create(HouseCreateArgument houseCreateArgument);

    Optional<HouseRecord> getById(String id);

    Page<HouseRecord> getAll(HouseSearchArgument houseSearchArgument, Pageable pageable);
}
