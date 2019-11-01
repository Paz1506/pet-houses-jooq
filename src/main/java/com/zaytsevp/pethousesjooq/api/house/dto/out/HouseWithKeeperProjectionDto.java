package com.zaytsevp.pethousesjooq.api.house.dto.out;

import lombok.*;

/**
 * Created by Pavel Zaytsev
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseWithKeeperProjectionDto {
    private String id;

    private String name;

    private Integer capacity;

    private String objectSize;

    private String status;

    private Boolean filled;

    private String houseKeeperId;

    private String houseKeeperFirstName;

    private String houseKeeperLastName;

    private Integer houseKeeperAge;

    private Integer houseKeeperLevel;
}
