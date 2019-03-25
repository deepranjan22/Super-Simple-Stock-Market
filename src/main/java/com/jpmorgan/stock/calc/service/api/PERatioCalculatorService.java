package com.jpmorgan.stock.calc.service.api;

import java.math.BigDecimal;

public interface PERatioCalculatorService extends StockCalcService {
    BigDecimal calculate(BigDecimal dividendYield, BigDecimal price);
}
