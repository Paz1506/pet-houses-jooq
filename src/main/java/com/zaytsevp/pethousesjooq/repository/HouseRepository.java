package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
@NoRepositoryBean
public interface HouseRepository {

    HouseRecord create(int capacity, boolean filled, String name, String objectSize);

    Optional<HouseRecord> getById(String id);
}
