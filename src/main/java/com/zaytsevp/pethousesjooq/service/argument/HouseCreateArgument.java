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
public class HouseCreateArgument {
    private Integer capacity;

    private Boolean filled;

    private String name;

    private String objectSize;

    private String houseKeeperId;
}
