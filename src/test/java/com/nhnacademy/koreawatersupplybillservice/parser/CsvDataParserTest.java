package com.nhnacademy.koreawatersupplybillservice.parser;

import static org.assertj.core.api.Assertions.assertThat;

import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class CsvDataParserTest {
    private DataParser dataParser;
    private List<RegionFee> regionFeeList;
    private String fileLocation;

    @BeforeEach
    void setUp() {
        dataParser = new CsvDataParser();
        regionFeeList = new ArrayList<>();
        fileLocation = "./tariff/Tariff_20220331.csv";
    }

    @DisplayName("파싱이 제대로 되고 있는지 확인")
    @Test
    void parseTest() {
        try (BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(
                new ClassPathResource(fileLocation).getInputStream()))) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowSt = line.split(",");
                regionFeeList.add(new RegionFee(rowSt[1], rowSt[2], Integer.parseInt(rowSt[3]),
                    Integer.parseInt(rowSt[4]), Integer.parseInt(rowSt[5]),
                    Integer.parseInt(rowSt[6])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(dataParser.parse(fileLocation))
            .usingRecursiveComparison()
            .isEqualTo(regionFeeList);
    }
}