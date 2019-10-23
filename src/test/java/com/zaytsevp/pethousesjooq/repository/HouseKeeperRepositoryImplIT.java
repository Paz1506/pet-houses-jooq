package com.zaytsevp.pethousesjooq.repository;

import com.zaytsevp.pethousesjooq.enums.EntityStatus;
import com.zaytsevp.pethousesjooq.model.tables.records.HouseKeeperRecord;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperCreateArgument;
import com.zaytsevp.pethousesjooq.service.argument.HouseKeeperSearchArgument;
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
class HouseKeeperRepositoryImplIT {

    @Autowired
    private HouseKeeperRepository houseKeeperRepository;

    private final String firstName = "first";

    private final String lastName = "last";

    private final Integer age = 18;

    private final Integer level = 5;

    @Test
    void create() {
        // Act
        HouseKeeperCreateArgument createArgument = HouseKeeperCreateArgument.builder()
                                                                            .firstName(firstName)
                                                                            .lastName(lastName)
                                                                            .age(age)
                                                                            .level(level)
                                                                            .build();

        HouseKeeperRecord actualResult = houseKeeperRepository.create(createArgument);

        // Assert
        Assertions.assertThat(actualResult).isNotNull();
        Assertions.assertThat(actualResult.getAge()).isEqualTo(age);
        Assertions.assertThat(actualResult.getLevel()).isEqualTo(level);
        Assertions.assertThat(actualResult.getFirstName()).isEqualTo(firstName);
        Assertions.assertThat(actualResult.getLastName()).isEqualTo(lastName);
        Assertions.assertThat(actualResult.getStatus()).isEqualTo(EntityStatus.ACTIVE.name());
    }

    @Test
    @Sql(value = "/datasets/repository/housekeeper/create.sql")
    void getById() {
        // Act
        Optional<HouseKeeperRecord> actualResult = houseKeeperRepository.getById("00000000-0000-0000-0000-000000000888");

        // Assert
        Assertions.assertThat(actualResult.isPresent()).isTrue();
        Assertions.assertThat(actualResult.get().getId()).isEqualTo("00000000-0000-0000-0000-000000000888");
        Assertions.assertThat(actualResult.get().getAge()).isEqualTo(30);
        Assertions.assertThat(actualResult.get().getLevel()).isEqualTo(9);
        Assertions.assertThat(actualResult.get().getFirstName()).isEqualTo("John");
        Assertions.assertThat(actualResult.get().getLastName()).isEqualTo("Smith");
    }

    @Test
    void getAll() {
        // Arrange
        HouseKeeperSearchArgument searchArgument = HouseKeeperSearchArgument.builder()
                                                                            .ageFrom(29)
                                                                            .ageTo(31)
                                                                            .levelFrom(8)
                                                                            .levelTo(10)
                                                                            .status(EntityStatus.ACTIVE)
                                                                            .firstName("n")
                                                                            .lastName("m")
                                                                            .build();

        // Act
        Page<HouseKeeperRecord> actualResultPage = houseKeeperRepository.getAll(searchArgument, PageRequest.of(0, 1));

        // Assert
        Assertions.assertThat(actualResultPage.getTotalElements()).isEqualTo(1);

        List<HouseKeeperRecord> content = actualResultPage.getContent();
        HouseKeeperRecord actualResult = content.get(0);

        Assertions.assertThat(actualResult.getId()).isEqualTo("00000000-0000-0000-0000-000000000000");
        Assertions.assertThat(actualResult.getAge()).isEqualTo(30);
        Assertions.assertThat(actualResult.getLevel()).isEqualTo(9);
        Assertions.assertThat(actualResult.getStatus()).isEqualTo(EntityStatus.ACTIVE.name());
        Assertions.assertThat(actualResult.getFirstName()).isEqualTo("John");
        Assertions.assertThat(actualResult.getLastName()).isEqualTo("Smith");
    }

    @Test
    void getAllWithPageable() {
        // Arrange
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "AGE");

        // Act
        Page<HouseKeeperRecord> actualResultPage = houseKeeperRepository.getAll(HouseKeeperSearchArgument.builder()
                                                                                                         .build(),
                                                                                pageRequest);

        // Assert
        Assertions.assertThat(actualResultPage.getTotalElements()).isEqualTo(3);

        List<HouseKeeperRecord> content = actualResultPage.getContent();
        Assertions.assertThat(content)
                  .extracting(HouseKeeperRecord::getId,
                              HouseKeeperRecord::getFirstName,
                              HouseKeeperRecord::getLastName,
                              HouseKeeperRecord::getAge,
                              HouseKeeperRecord::getLevel,
                              HouseKeeperRecord::getStatus)
                  .containsExactly(Tuple.tuple("00000000-0000-0000-0000-000000000002", "Barak", "Obama", 50, 1, "ACTIVE"),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000004", "Peter", "Ivanov", 35, 7, "ACTIVE"),
                                   Tuple.tuple("00000000-0000-0000-0000-000000000000", "John", "Smith", 30, 9, "ACTIVE"));
    }
}