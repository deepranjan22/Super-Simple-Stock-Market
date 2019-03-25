package com.jpmorgan.stock.calc.facts;

public enum StockType {
    COMMON("Common"),
    PREFERRED("Preferred");

    private String name;
    StockType(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
