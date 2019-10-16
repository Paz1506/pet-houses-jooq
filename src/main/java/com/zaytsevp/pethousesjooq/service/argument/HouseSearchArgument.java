package com.zaytsevp.pethousesjooq.service.argument;

import com.zaytsevp.pethousesjooq.enums.ObjectSize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Pavel Zaytsev
 */
@Getter
@Setter
@Builder
public class HouseSearchArgument {
    private String id;

    private Boolean filled;

    private ObjectSize objectSize;

    private String name;

    private Integer capacityFrom;

    private Integer capacityTo;
}
