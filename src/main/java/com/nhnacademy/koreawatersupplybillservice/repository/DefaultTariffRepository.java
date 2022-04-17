package com.nhnacademy.koreawatersupplybillservice.repository;

import com.nhnacademy.koreawatersupplybillservice.parser.DataParser;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultTariffRepository implements TariffRepository {
    private final List<RegionFee> regionFeeList = new ArrayList<>();
    private final DataParser dataParser;

    @Autowired
    public DefaultTariffRepository(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    @Override
    public void load(String fileLocation) {
        regionFeeList.addAll(dataParser.parse(fileLocation));
    }

    @Override
    public List<RegionFee> findFeesBaseOnUsage(long waterUsage) {
        return regionFeeList.stream().filter(regionFee -> regionFee.getUnitStart() <= waterUsage &&
            waterUsage <= regionFee.getUnitEnd()).collect(
            Collectors.toList());
    }

    @Override
    public List<RegionFee> regionFeeListFindAll() {
        return this.regionFeeList;
    }

}
