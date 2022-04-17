package com.nhnacademy.koreawatersupplybillservice.service;

import java.util.List;

public interface WaterSupplyBillService {
    List<WaterBill> calculateFee(long waterUsage);
}
