package com.zaytsevp.pethousesjooq.api.housekeeper.dto.out;

import lombok.*;

/**
 * Created by Pavel Zaytsev
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HouseKeeperDto {
    private String id;

    private String firstName;

    private String lastName;

    private Integer age;

    private Integer level;

    private String status;
}
