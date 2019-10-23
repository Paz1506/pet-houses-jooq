package com.zaytsevp.pethousesjooq.service.argument;

import com.zaytsevp.pethousesjooq.enums.EntityStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pavel Zaytsev
 */
@Getter
@Setter
@Builder
public class HouseKeeperSearchArgument {
    private String id;

    private String firstName;

    private String lastName;

    private Integer ageFrom;

    private Integer ageTo;

    private Integer levelFrom;

    private Integer levelTo;

    private EntityStatus status;
}
