package com.zaytsevp.pethousesjooq.service;

import com.zaytsevp.pethousesjooq.enums.ObjectSize;
import com.zaytsevp.pethousesjooq.exceptions.RecordNotFoundException;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.repository.HouseRepository;
import com.zaytsevp.pethousesjooq.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseServiceImpl(HouseRepository houseRepository) {this.houseRepository = houseRepository;}

    @Override
    @Transactional
    public HouseRecord create(Integer capacity, Boolean filled, String name, ObjectSize objectSize) {
        Validator.validateStringParam(name, "Не передано имя дома");
        Validator.validateObjectParam(objectSize, "Не передан размер дома");
        Validator.validateObjectParam(filled, "Не передан признак заполненности дома");
        Validator.validateByCondition(capacity != null && capacity > 0, "Некорректное значение параметра вместимости дома");

        return houseRepository.create(capacity, filled, name, objectSize.name());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HouseRecord> getById(String id) {
        Validator.validateStringParam(id, "Не передан идентификатор дома");

        return houseRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HouseRecord getExistingById(String id) {
        return getById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }
}
