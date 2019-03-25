package com.jpmorgan.stock.calc.facts;

import java.math.BigDecimal;

public enum Stock {
    TEA("TEA", "Common", BigDecimal.ZERO, null, new BigDecimal(100)),
    POP("POP", "Common", new BigDecimal(8), null, new BigDecimal(100)),
    ALE("ALE", "Common", new BigDecimal(23), null, new BigDecimal(60)),
    GIN("GIN", "Preferred", new BigDecimal(8), new BigDecimal(2), new BigDecimal(100)),
    JOE("JOE", "Common", new BigDecimal(13), null, new BigDecimal(250));

    private String symbol;
    private String type;
    private BigDecimal lastDividend;
    private BigDecimal fixedDividend;
    private BigDecimal parValue;

    Stock(String symbol, String type, BigDecimal lastDividend, BigDecimal fixedDividend, BigDecimal parValue) {
        this.symbol = symbol;
        this.type = type;
        this.lastDividend = lastDividend;
        this.fixedDividend = fixedDividend;
        this.parValue = parValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getLastDividend() {
        return lastDividend;
    }

    public BigDecimal getFixedDividend() {
        return fixedDividend;
    }

    public BigDecimal getParValue() {
        return parValue;
    }
}
