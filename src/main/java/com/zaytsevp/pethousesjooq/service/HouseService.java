package com.zaytsevp.pethousesjooq.service;

import com.zaytsevp.pethousesjooq.enums.ObjectSize;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;

import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
public interface HouseService {

    HouseRecord create(Integer capacity, Boolean filled, String name, ObjectSize objectSize);

    Optional<HouseRecord> getById(String id);

    HouseRecord getExistingById(String id);
}
