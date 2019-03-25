package com.jpmorgan.stock.calc.service.api;

import com.jpmorgan.stock.calc.facts.Stock;

import java.math.BigDecimal;

public interface DividendYieldCalculatorService extends StockCalcService {
    BigDecimal calculate(Stock stock, BigDecimal price);
}
