package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class HouseRepositoryImplIT {

    @Autowired
    private HouseRepository houseRepository;

    @Test
    void create() {
        //
        houseRepository.create(1, false, "one", "S");
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
}