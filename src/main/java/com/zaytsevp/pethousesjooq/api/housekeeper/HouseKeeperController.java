package com.zaytsevp.pethousesjooq.api.housekeeper;

import com.zaytsevp.petHouseKeepersjooq.service.HouseKeeperService;
import com.zaytsevp.pethousesjooq.api.housekeeper.dto.out.HouseKeeperDto;
import com.zaytsevp.pethousesjooq.api.housekeeper.mapper.HouseKeeperMapper;
import com.zaytsevp.pethousesjooq.enums.EntityStatus;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pavel Zaytsev
 */
@RestController
@RequestMapping("/house-keepers")
public class HouseKeeperController {

    private final HouseKeeperService houseKeeperService;

    private final HouseKeeperMapper houseKeeperMapper;

    @Autowired
    public HouseKeeperController(HouseKeeperService houseKeeperService,
                                 HouseKeeperMapper houseKeeperMapper) {
        this.houseKeeperService = houseKeeperService;
        this.houseKeeperMapper = houseKeeperMapper;
    }

    @GetMapping("{id}")
    public HouseKeeperDto getById(@PathVariable String id) {
        return houseKeeperMapper.toDto(houseKeeperService.getExistingById(id));
    }

    @GetMapping("/list")
    public List<HouseKeeperDto> getAll(@RequestParam(name = "firstName", required = false) String firstName,
                                       @RequestParam(name = "lastName", required = false) String lastName,
                                       @RequestParam(name = "status", required = false) EntityStatus status,
                                       @RequestParam(name = "ageFrom", required = false) Integer ageFrom,
                                       @RequestParam(name = "ageTo", required = false) Integer ageTo,
                                       @RequestParam(name = "levelFrom", required = false) Integer levelFrom,
                                       @RequestParam(name = "levelTo", required = false) Integer levelTo,
                                       @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                       @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(name = "sortField", defaultValue = "first_name") String sortField,
                                       @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection) {
        return houseKeeperService.getAll(HouseKeeperSearchArgument.builder()
                                                                  .firstName(firstName)
                                                                  .lastName(lastName)
                                                                  .status(status)
                                                                  .ageFrom(ageFrom)
                                                                  .ageTo(ageTo)
                                                                  .levelFrom(levelFrom)
                                                                  .levelTo(levelTo)
                                                                  .build(),
                                         PageRequest.of(pageNumber,
                                                        pageSize,
                                                        Sort.by(sortDirection, sortField)))
                                 .stream()
                                 .map(houseKeeperMapper::toDto)
                                 .collect(Collectors.toList());
    }
}
