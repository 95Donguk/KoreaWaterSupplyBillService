package com.nhnacademy.koreawatersupplybillservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.koreawatersupplybillservice.repository.DefaultTariffRepository;
import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import com.nhnacademy.koreawatersupplybillservice.repository.TariffRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultWaterSupplyBillServiceTest {
    TariffRepository repository;
    WaterSupplyBillService service;
    List<WaterBill> waterBillList;

    @BeforeEach
    void setUp() {
        repository = mock(DefaultTariffRepository.class);
        service = new DefaultWaterSupplyBillService(repository);
        waterBillList = new ArrayList<>();
    }

    @DisplayName("물 사용량에 따른 요금이 정확한 계산을 이루어 내는지 확인")
    @Test
    void calculateFee() {
        long waterUsage = 1000;
        List<RegionFee> regionFeeList = new ArrayList<>();
        regionFeeList.add(new RegionFee("동두천시", "가정용", 3, 31, 999999, 1530));
        regionFeeList.add(new RegionFee("파주시", "일반용", 4, 301, 9999999, 1620));
        regionFeeList.add(new RegionFee("광주시", "대중탕용", 1, 1, 1000, 760));
        when(repository.findFeesBaseOnUsage(waterUsage)).thenReturn(regionFeeList);
        for (RegionFee regionFee : regionFeeList) {
            waterBillList.add(
                new WaterBill(regionFee.getCity(), regionFee.getSector(), regionFee.getUnitPrice(),
                    regionFee.getUnitPrice() * waterUsage));
        }
        assertThat(service.calculateFee(waterUsage)).usingRecursiveComparison()
            .isEqualTo(waterBillList);
    }
}