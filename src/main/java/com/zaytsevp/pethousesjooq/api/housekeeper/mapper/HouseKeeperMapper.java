package com.zaytsevp.pethousesjooq.api.housekeeper.mapper;

import com.zaytsevp.pethousesjooq.api.housekeeper.dto.out.HouseKeeperDto;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import org.mapstruct.Mapper;

/**
 * Created by Pavel Zaytsev
 */
@Mapper
public interface HouseKeeperMapper {

    HouseKeeperDto toDto(HouseKeeperRecord record);
}
