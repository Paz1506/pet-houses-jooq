package com.zaytsevp.pethousesjooq.service;

import com.zaytsevp.pethousesjooq.exceptions.RecordNotFoundException;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.projections.HouseWithKeeperProjection;
import com.zaytsevp.pethousesjooq.repository.HouseRepository;
import com.zaytsevp.pethousesjooq.service.argument.HouseCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import com.zaytsevp.pethousesjooq.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Pavel Zaytsev
 */
@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseServiceImpl(HouseRepository houseRepository) {this.houseRepository = houseRepository;}

    // TODO: filled не задавать руками, а вычислять на основании capacity & количества питомцев в нем
    @Override
    @Transactional
    public HouseRecord create(HouseCreateArgument houseCreateArgument) {
        Validator.validateStringParam(houseCreateArgument.getName(), "Не передано имя дома");
        Validator.validateStringParam(houseCreateArgument.getHouseKeeperId(), "Не передан ответственный сотрудник");
        Validator.validateObjectParam(houseCreateArgument.getObjectSize(), "Не передан размер дома");
        Validator.validateObjectParam(houseCreateArgument.getFilled(), "Не передан признак заполненности дома");
        Validator.validateByCondition(houseCreateArgument.getCapacity() != null
                                      && houseCreateArgument.getCapacity() > 0, "Некорректное значение параметра вместимости дома");

        return houseRepository.create(houseCreateArgument);
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

    @Override
    @Transactional(readOnly = true)
    public List<HouseRecord> getAll(HouseSearchArgument searchArgument, Pageable pageable) {
        return houseRepository.getAll(searchArgument, pageable)
                              .getContent();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HouseWithKeeperProjection> getProjectionWithKeeperById(String id) {
        Validator.validateStringParam(id, "Не передан идентификатор дома");

        return houseRepository.getProjectionWithKeeperById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HouseWithKeeperProjection getExistingProjectionWithKeeperById(String id) {
        return getProjectionWithKeeperById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }
}
