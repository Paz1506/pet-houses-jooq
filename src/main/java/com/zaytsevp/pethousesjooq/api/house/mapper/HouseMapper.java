package com.zaytsevp.pethousesjooq.api.house.mapper;

import com.zaytsevp.pethousesjooq.api.house.dto.out.HouseDto;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import org.mapstruct.Mapper;

/**
 * Created by Pavel Zaytsev
 */
@Mapper
public interface HouseMapper {

    HouseDto toDto(HouseRecord record);
}
