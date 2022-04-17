package com.nhnacademy.koreawatersupplybillservice.service;

public class WaterBill {
    private final String city;
    private final String sector;
    private final int unitPrice;
    private final long billTotal;

    public WaterBill(String city, String sector, int unitPrice, long billTotal) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
        this.billTotal = billTotal;
    }

    @Override
    public String toString() {
        return "WaterBill{" +
            "city='" + city + '\'' +
            ", sector='" + sector + '\'' +
            ", unitPrice=" + unitPrice +
            ", billTotal=" + billTotal +
            "}\n";
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
