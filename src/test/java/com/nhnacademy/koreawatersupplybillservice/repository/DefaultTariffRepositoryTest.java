package com.nhnacademy.koreawatersupplybillservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.koreawatersupplybillservice.parser.CsvDataParser;
import com.nhnacademy.koreawatersupplybillservice.parser.DataParser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultTariffRepositoryTest {
    private TariffRepository repository;
    private DataParser dataParser;
    private String fileLocation;
    private List<RegionFee> regionFeeList;

    @BeforeEach
    void setUp() {
        dataParser = mock(CsvDataParser.class);
        repository = new DefaultTariffRepository(dataParser);
        fileLocation = "./tariff/Tariff_20220331.csv";
        regionFeeList = new ArrayList<>();
        List<RegionFee> testList = new ArrayList<>();
        testList.add(new RegionFee("동두천시", "가정용", 3, 31, 999999, 1530));
        testList.add(new RegionFee("파주시", "일반용", 4, 301, 9999999, 1620));
        testList.add(new RegionFee("광주시", "대중탕용", 1, 1, 1000, 760));
        when(dataParser.parse(fileLocation)).thenReturn(testList);
    }

    @DisplayName("파싱이 완료된 리스트를 복제해오는지 확인")
    @Test
    void loadTest() {
        regionFeeList.addAll(dataParser.parse(fileLocation));
        repository.load(fileLocation);
        assertThat(repository.regionFeeListFindAll()).containsAll(regionFeeList);
    }

    @DisplayName("물 사용량에 따른 요금표를 필터링한 새로운 리스트로 만드는지 확인")
    @Test
    void findFeesBaseOnUsage() {
        long waterUsage = 1000;
        regionFeeList.addAll(dataParser.parse(fileLocation));
        repository.load(fileLocation);
        assertThat(repository.findFeesBaseOnUsage(waterUsage)).containsAll(regionFeeList.stream()
            .filter(regionFee -> regionFee.getUnitStart() <= waterUsage &&
                waterUsage <= regionFee.getUnitEnd()).collect(
                Collectors.toList()));
    }
}