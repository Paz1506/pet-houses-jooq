package com.zaytsevp.pethousesjooq.service;

import com.zaytsevp.petHouseKeepersjooq.service.HouseKeeperService;
import com.zaytsevp.pethousesjooq.exceptions.RecordNotFoundException;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.repository.HouseKeeperRepository;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
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
public class HouseKeeperServiceImpl implements HouseKeeperService {

    private final HouseKeeperRepository houseKeeperRepository;

    @Autowired
    public HouseKeeperServiceImpl(HouseKeeperRepository houseKeeperRepository) {this.houseKeeperRepository = houseKeeperRepository;}

    @Override
    @Transactional
    public HouseKeeperRecord create(HouseKeeperCreateArgument houseKeeperCreateArgument) {
        Validator.validateStringParam(houseKeeperCreateArgument.getFirstName(), "Не передано имя");
        Validator.validateStringParam(houseKeeperCreateArgument.getLastName(), "Не передана фамилия");
        Validator.validateObjectParam(houseKeeperCreateArgument.getAge(), "Не передан возраст");
        Validator.validateByCondition(houseKeeperCreateArgument.getAge() > 0
                                      && houseKeeperCreateArgument.getAge() < 102, "Некорректное значение возраста");
        Validator.validateObjectParam(houseKeeperCreateArgument.getLevel(), "Не передан уровень");
        Validator.validateByCondition(houseKeeperCreateArgument.getLevel() > 0
                                      && houseKeeperCreateArgument.getLevel() < 100, "Некорректное значение уровня");

        return houseKeeperRepository.create(houseKeeperCreateArgument);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HouseKeeperRecord> getById(String id) {
        Validator.validateStringParam(id, "Не передан идентификатор смотрителя");

        return houseKeeperRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HouseKeeperRecord getExistingById(String id) {
        return getById(id).orElseThrow(() -> new RecordNotFoundException("Record not found!"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HouseKeeperRecord> getAll(HouseKeeperSearchArgument searchArgument, Pageable pageable) {
        return houseKeeperRepository.getAll(searchArgument, pageable)
                                    .getContent();
    }
}
