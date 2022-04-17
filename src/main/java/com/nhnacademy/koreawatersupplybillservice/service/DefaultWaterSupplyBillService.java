package com.nhnacademy.koreawatersupplybillservice.service;

import com.nhnacademy.koreawatersupplybillservice.repository.RegionFee;
import com.nhnacademy.koreawatersupplybillservice.repository.TariffRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class DefaultWaterSupplyBillService implements WaterSupplyBillService {
    private final TariffRepository tariffRepository;
    private final List<WaterBill> waterBillList = new ArrayList<>();

    @Autowired
    public DefaultWaterSupplyBillService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    @Override
    public List<WaterBill> calculateFee(long waterUsage) {
        for (RegionFee regionFee : tariffRepository.findFeesBaseOnUsage(waterUsage)) {
            waterBillList.add(
                new WaterBill(regionFee.getCity(), regionFee.getSector(),
                    regionFee.getUnitPrice(), regionFee.getUnitPrice() * waterUsage));
        }
        return waterBillList;
    }
}