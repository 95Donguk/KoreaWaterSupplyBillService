package com.nhnacademy.koreawatersupplybillservice.parser;

import static java.lang.Integer.parseInt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;

public class JsonDataParser implements DataParser {
    private final List<RegionFee> regionFeeList = new ArrayList<>();
    private static final Log log = LogFactory.getLog(JsonDataParser.class);

    @Override
    public List<RegionFee> parse(String fileLocation) {
        var mapper = new ObjectMapper();
        TypeReference<List<HashMap<String, String>>> typeReference = new TypeReference<>() {
        };
        try (var bufferedReader = new BufferedReader(
            new InputStreamReader(new ClassPathResource(fileLocation).getInputStream()))) {
            List<HashMap<String, String>> list = mapper.readValue(bufferedReader, typeReference);
            list.stream().forEach(regionFeeHashMap -> regionFeeList.add(
                new RegionFee(regionFeeHashMap.get("지자체명"), regionFeeHashMap.get("업종"),
                    parseInt(regionFeeHashMap.get("단계")),
                    parseInt(regionFeeHashMap.get("구간시작(세제곱미터)")),
                    parseInt(regionFeeHashMap.get("구간끝(세제곱미터)")),
                    parseInt(regionFeeHashMap.get("구간금액(원)")))));
        } catch (IOException e) {
            log.warn("파일을 불러오지 못했습니다.");
        }
        return regionFeeList;
    }
}
