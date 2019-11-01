package com.zaytsevp.pethousesjooq.api.house.mapper;

import com.zaytsevp.pethousesjooq.api.house.dto.out.HouseWithKeeperProjectionDto;
import com.zaytsevp.pethousesjooq.projections.HouseWithKeeperProjection;
import org.mapstruct.Mapper;

/**
 * Created by Pavel Zaytsev
 */
@Mapper
public interface HouseWithKeeperProjectionMapper {

    HouseWithKeeperProjectionDto toDto(HouseWithKeeperProjection projection);
}
