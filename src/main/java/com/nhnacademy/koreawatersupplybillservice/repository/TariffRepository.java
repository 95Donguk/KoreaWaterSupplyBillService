package com.nhnacademy.koreawatersupplybillservice.repository;

import java.util.List;

public interface TariffRepository {
    void load(String fileLocation);

    List<RegionFee> findFeesBaseOnUsage(long waterUsage);

    List<RegionFee> regionFeeListFindAll();
}
