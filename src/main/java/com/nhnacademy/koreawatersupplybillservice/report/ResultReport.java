package com.nhnacademy.koreawatersupplybillservice.report;

import com.nhnacademy.koreawatersupplybillservice.service.WaterBill;
import java.util.Collection;

public interface ResultReport {
    boolean report(Collection<WaterBill> waterBills);
}
