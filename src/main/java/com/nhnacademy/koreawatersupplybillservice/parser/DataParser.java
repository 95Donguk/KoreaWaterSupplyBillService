package com.nhnacademy.koreawatersupplybillservice.parser;

import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import java.util.List;

public interface DataParser {
    List<RegionFee> parse(String fileLocation);
}
