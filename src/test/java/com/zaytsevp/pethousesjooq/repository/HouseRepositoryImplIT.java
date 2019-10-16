package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.enums.ObjectSize;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class HouseRepositoryImplIT {

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void create() {
        // Act
        HouseRecord actualResult = houseRepository.create(1, false, "one", "S");

        // Assert
        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult.getCapacity()).isEqualTo(1);
        Assertions.assertThat(actualResult.getFilled()).isFalse();
        Assertions.assertThat(actualResult.getName()).isEqualTo("one");
        Assertions.assertThat(actualResult.getObjectSize()).isEqualTo("S");
    }

    @Test
    void getById() {
        // Act
        Optional<HouseRecord> actualResult = houseRepository.getById("00000000-0000-0000-0000-000000000000");

        // Assert
        Assertions.assertThat(actualResult.isPresent()).isTrue();
        Assertions.assertThat(actualResult.get().getId()).isEqualTo("00000000-0000-0000-0000-000000000000");
        Assertions.assertThat(actualResult.get().getCapacity()).isEqualTo(1);
        Assertions.assertThat(actualResult.get().getName()).isEqualTo("House1");
        Assertions.assertThat(actualResult.get().getFilled()).isFalse();
    }

    @Test
    void getAll() {
        // Arrange
        HouseSearchArgument searchArgument = HouseSearchArgument.builder()
                                                                .id("00000000-0000-0000-0000-000000000011")
                                                                .filled(true)
                                                                .name("andDo")
                                                                .objectSize(ObjectSize.L)
                                                                .capacityFrom(3)
                                                                .capacityTo(5)
                                                                .build();

        // Act
        Page<HouseRecord> actualResultPage = houseRepository.getAll(searchArgument, PageRequest.of(1, 1));

        // Assert
        Assertions.assertThat(actualResultPage.getTotalElements()).isEqualTo(1);

        List<HouseRecord> content = actualResultPage.getContent();
        Assertions.assertThat(content.get(0).getId()).isEqualTo("00000000-0000-0000-0000-000000000011");
        Assertions.assertThat(content.get(0).getFilled()).isEqualTo(true);
        Assertions.assertThat(content.get(0).getObjectSize()).isEqualTo("L");
        Assertions.assertThat(content.get(0).getName()).isEqualTo("LandDog");
    }
}