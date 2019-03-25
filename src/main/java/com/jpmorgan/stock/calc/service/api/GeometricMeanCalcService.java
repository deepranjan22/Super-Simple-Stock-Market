package com.jpmorgan.stock.calc.service.api;

import com.jpmorgan.stock.calc.entity.Trade;

import java.math.BigDecimal;
import java.util.List;

public interface GeometricMeanCalcService extends StockCalcService {
    BigDecimal calculate(List<Trade> trades);
}
