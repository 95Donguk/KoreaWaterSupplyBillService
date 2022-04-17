package com.nhnacademy.koreawatersupplybillservice;

import com.nhnacademy.koreawatersupplybillservice.report.ResultReport;
import com.nhnacademy.koreawatersupplybillservice.repository.TariffRepository;
import com.nhnacademy.koreawatersupplybillservice.service.WaterBill;
import com.nhnacademy.koreawatersupplybillservice.service.WaterSupplyBillService;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {
    private static final Log log = LogFactory.getLog(BootStrap.class);

    public static void main(String[] args) {
        try (var context =
                 new AnnotationConfigApplicationContext(
                     "com.nhnacademy.koreawatersupplybillservice")) {

            var fileLocation = "./tariff/Tariff_20220331.csv";

            context.getBean("tariffRepository", TariffRepository.class)
                .load(fileLocation);

            int inputWaterUsage = getInputWaterUsage();
            log.info("물 사용량에 따른 요금표");
            log.info(context.getBean("tariffRepository", TariffRepository.class)
                .findFeesBaseOnUsage(inputWaterUsage));
            List<WaterBill> waterBillList =
                context.getBean("waterSupplyBillService", WaterSupplyBillService.class)
                    .calculateFee(inputWaterUsage);
            log.info("상수도 최저가 영수증");
            context.getBean("resultReport", ResultReport.class).report(waterBillList);
        }
    }

    private static int getInputWaterUsage() {
        var scanner = new Scanner(System.in);
        log.info("> ");
        return scanner.nextInt();
    }
}
