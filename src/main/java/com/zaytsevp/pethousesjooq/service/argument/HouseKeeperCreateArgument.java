package com.zaytsevp.pethousesjooq.service.argument;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pavel Zaytsev
 */
@Getter
@Setter
@Builder
public class HouseKeeperCreateArgument {
    private String firstName;

    private String lastName;

    private Integer age;

    private Integer level;
}
