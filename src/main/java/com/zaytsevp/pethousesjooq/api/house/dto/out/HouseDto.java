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
public class HouseDto {
    private String id;

    private String name;

    private Integer capacity;

    private String objectSize;

    private String status;

    private Boolean filled;
}
