package com.nhnacademy.koreawatersupplybillservice.report;

import com.nhnacademy.koreawatersupplybillservice.service.WaterBill;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DefaultResultReport implements ResultReport {
    private static final Log log = LogFactory.getLog(DefaultResultReport.class);

    @Override
    public boolean report(Collection<WaterBill> waterBills) {
        log.info(
            waterBills.stream().sorted(Comparator.comparing(WaterBill::getUnitPrice)).limit(5)
                .collect(
                    Collectors.toList()));
        return true;
    }
}
