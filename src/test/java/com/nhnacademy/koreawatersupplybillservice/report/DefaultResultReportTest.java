package com.nhnacademy.koreawatersupplybillservice.report;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.koreawatersupplybillservice.service.WaterBill;
import com.nhnacademy.koreawatersupplybillservice.service.DefaultWaterSupplyBillService;
import com.nhnacademy.koreawatersupplybillservice.service.WaterSupplyBillService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultResultReportTest {
    private WaterSupplyBillService waterSupplyBillService;
    private ResultReport resultReport;

    @BeforeEach
    void setUp() {
        waterSupplyBillService = mock(DefaultWaterSupplyBillService.class);
        resultReport = new DefaultResultReport();
    }

    @DisplayName("영수증이 최저가순으로 5개의 리스트가 잘 출력되는지 확인")
    @Test
    void reportTest() {
        List<WaterBill> waterBillList = new ArrayList<>();
        waterBillList.add(new WaterBill("창원시", "일반용", 1500, 1500000));
        waterBillList.add(new WaterBill("김해시", "공업용", 500, 500000));
        waterBillList.add(new WaterBill("부산시", "일반용", 1800, 1800000));
        waterBillList.add(new WaterBill("양산시", "공업용", 300, 300000));
        waterBillList.add(new WaterBill("진주시", "일반용", 200, 200000));
        waterBillList.add(new WaterBill("사천시", "가정용", 1200, 1200000));
        when(waterSupplyBillService.calculateFee(1000)).thenReturn(waterBillList);
        assertThat(resultReport.report(waterBillList)).isTrue();
    }
}