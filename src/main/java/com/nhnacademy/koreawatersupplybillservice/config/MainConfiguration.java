package com.nhnacademy.koreawatersupplybillservice.config;

import com.nhnacademy.koreawatersupplybillservice.parser.CsvDataParser;
import com.nhnacademy.koreawatersupplybillservice.parser.DataParser;
import com.nhnacademy.koreawatersupplybillservice.parser.JsonDataParser;
import com.nhnacademy.koreawatersupplybillservice.report.DefaultResultReport;
import com.nhnacademy.koreawatersupplybillservice.report.ResultReport;
import com.nhnacademy.koreawatersupplybillservice.repository.DefaultTariffRepository;
import com.nhnacademy.koreawatersupplybillservice.repository.TariffRepository;
import com.nhnacademy.koreawatersupplybillservice.service.DefaultWaterSupplyBillService;
import com.nhnacademy.koreawatersupplybillservice.service.WaterSupplyBillService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class MainConfiguration {

    @Bean
    public DataParser jsonDataParser() {
        return new JsonDataParser();
    }

    @Bean
    public DataParser csvDataParser() {
        return new CsvDataParser();
    }

    @Bean
    public ResultReport resultReport() {
        return new DefaultResultReport();
    }

    @Bean
    public TariffRepository tariffRepository(@Qualifier("csvDataParser") DataParser dataParser) {
        return new DefaultTariffRepository(dataParser);
    }

    @Bean
    public WaterSupplyBillService waterSupplyBillService(TariffRepository tariffRepository) {
        return new DefaultWaterSupplyBillService(tariffRepository);
    }
}
