package com.nhnacademy.koreawatersupplybillservice.parser;

import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

public class CsvDataParser implements DataParser {
    private final List<RegionFee> regionFeeList = new ArrayList<>();
    private static final Log log = LogFactory.getLog(CsvDataParser.class);

    @Override
    public List<RegionFee> parse(String fileLocation) {
        try (var bufferedReader = new BufferedReader(
            new InputStreamReader(new ClassPathResource(fileLocation).getInputStream()))) {
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] rowSt = line.split(",");
                regionFeeList.add(new RegionFee(rowSt[1], rowSt[2], Integer.parseInt(rowSt[3]),
                    Integer.parseInt(rowSt[4]), Integer.parseInt(rowSt[5]),
                    Integer.parseInt(rowSt[6])));
            }
        } catch (IOException e) {
            log.warn("파일을 불러오지 못했습니다.");
        }
        return regionFeeList;
    }
}
