package com.zaytsevp.pethousesjooq.api.house;

import com.zaytsevp.pethousesjooq.api.house.dto.out.HouseDto;
import com.zaytsevp.pethousesjooq.api.house.dto.out.HouseWithKeeperProjectionDto;
import com.zaytsevp.pethousesjooq.api.house.mapper.HouseMapper;
import com.zaytsevp.pethousesjooq.api.house.mapper.HouseWithKeeperProjectionMapper;
import com.zaytsevp.pethousesjooq.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pavel Zaytsev
 */
@RestController
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    private final HouseMapper houseMapper;

    private final HouseWithKeeperProjectionMapper houseWithKeeperProjectionMapper;

    @Autowired
    public HouseController(HouseService houseService,
                           HouseMapper houseMapper,
                           HouseWithKeeperProjectionMapper houseWithKeeperProjectionMapper) {
        this.houseService = houseService;
        this.houseMapper = houseMapper;
        this.houseWithKeeperProjectionMapper = houseWithKeeperProjectionMapper;
    }

    @GetMapping("{id}")
    public HouseDto getById(@PathVariable String id) {
        return houseMapper.toDto(houseService.getExistingById(id));
    }

    @GetMapping("projections/{id}")
    public HouseWithKeeperProjectionDto getWithKeeperProjectionById(@PathVariable String id) {
        return houseWithKeeperProjectionMapper.toDto(houseService.getExistingProjectionWithKeeperById(id));
    }
}
