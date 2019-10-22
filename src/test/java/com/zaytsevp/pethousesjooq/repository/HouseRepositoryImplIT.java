package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.enums.EntityStatus;
import com.zaytsevp.pethousesjooq.enums.ObjectSize;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseSearchArgument;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class HouseRepositoryImplIT {

    @Autowired
    private HouseRepository houseRepository;

    @Test
    @Sql(value = "/datasets/repository/house/create.sql")
    void create() {
        // Act
        String houseKeeperId = "00000000-0000-0000-0000-000000000777";

        HouseCreateArgument createArgument = HouseCreateArgument.builder()
                                                                .name("one")
                                                                .filled(false)
                                                                .capacity(1)
                                                                .objectSize(ObjectSize.M.name())
                                                                .houseKeeperId(houseKeeperId)
                                                                .build();

        HouseRecord actualResult = houseRepository.create(createArgument);

        // Assert
        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult.getCapacity()).isEqualTo(1);
        Assertions.assertThat(actualResult.getFilled()).isFalse();
        Assertions.assertThat(actualResult.getName()).isEqualTo("one");
        Assertions.assertThat(actualResult.getStatus()).isEqualTo(EntityStatus.ACTIVE.name());
        Assertions.assertThat(actualResult.getHouseKeeperId()).isEqualTo(houseKeeperId);
        Assertions.assertThat(actualResult.getObjectSize()).isEqualTo(ObjectSize.M.name());
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
                                                                .capacityFrom(11)
                                                                .capacityTo(13)
                                                                .build();

        // Act
        Page<HouseRecord> actualResultPage = houseRepository.getAll(searchArgument, PageRequest.of(0, 1));

        // Assert
        Assertions.assertThat(actualResultPage.getTotalElements()).isEqualTo(1);

        List<HouseRecord> content = actualResultPage.getContent();
        HouseRecord houseRecord = content.get(0);

        Assertions.assertThat(houseRecord.getId()).isEqualTo("00000000-0000-0000-0000-000000000011");
        Assertions.assertThat(houseRecord.getFilled()).isEqualTo(true);
        Assertions.assertThat(houseRecord.getObjectSize()).isEqualTo("L");
        Assertions.assertThat(houseRecord.getCapacity()).isEqualTo(12);
        Assertions.assertThat(houseRecord.getName()).isEqualTo("LandDog");
    }

    @Test
    void getAllWithPageable() {
        // Arrange
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.Direction.DESC, "CAPACITY");

        // Act
        Page<HouseRecord> actualResultPage = houseRepository.getAll(HouseSearchArgument.builder()
                                                                                       .build(),
                                                                    pageRequest);

        // Assert
        Assertions.assertThat(actualResultPage.getTotalElements()).isEqualTo(5);

        List<HouseRecord> content = actualResultPage.getContent();
        Assertions.assertThat(content)
                  .extracting(HouseRecord::getId,
                              HouseRecord::getFilled,
                              HouseRecord::getObjectSize,
                              HouseRecord::getName,
                              HouseRecord::getCapacity)
                  .containsExactly(Tuple.tuple("00000000-0000-0000-0000-000000000010", false, "L", "CatLand", 11),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000009", false, "M", "Kingdom2", 10),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000008", false, "S", "Kingdom", 9),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000007", false, "XS", "Hole", 8),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000006", true, "XL", "Castle", 7));
    }
}