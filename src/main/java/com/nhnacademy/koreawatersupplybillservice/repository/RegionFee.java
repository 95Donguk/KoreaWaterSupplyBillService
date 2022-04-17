package com.nhnacademy.koreawatersupplybillservice.repository;

public class RegionFee {
    private final String city;
    private final String sector;
    private final int step;
    private final int unitStart;
    private final int unitEnd;
    private final int unitPrice;

    public RegionFee(String city, String sector, int step, int unitStart, int sectionEnd,
                     int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.step = step;
        this.unitStart = unitStart;
        this.unitEnd = sectionEnd;
        this.unitPrice = unitPrice;
    }

    public String getCity() {
        return this.city;
    }

    public String getSector() {
        return this.sector;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public int getUnitStart() {
        return unitStart;
    }

    public int getUnitEnd() {
        return unitEnd;
    }

    @Override
    public String toString() {
        return "RegionFee{" +
            "city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", step=" + step +
            ", unitStart=" + unitStart +
            ", unitEnd=" + unitEnd +
            ", unitPrice=" + unitPrice +
            "}\n";
    }
}
