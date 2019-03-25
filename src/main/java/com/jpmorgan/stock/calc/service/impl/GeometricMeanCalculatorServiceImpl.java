package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.entity.Trade;
import com.jpmorgan.stock.calc.service.api.GeometricMeanCalcService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class GeometricMeanCalculatorServiceImpl implements GeometricMeanCalcService {
    private static final Logger logger = Logger.getLogger(GeometricMeanCalculatorServiceImpl.class);
    @Override
    public BigDecimal calculate(List<Trade> trades) {
        if (Objects.isNull(trades) || trades.isEmpty()) {
            logger.warn("Trades are empty");
            return BigDecimal.ZERO;
        }

        BigDecimal multiplied = BigDecimal.ONE;

        for (Trade trade : trades) {
            multiplied = multiplied.multiply(trade.getTradedPrice());
        }

        double n = BigDecimal.ONE.divide(BigDecimal.valueOf(trades.size()), DEFAULT_MATH_CONTEXT).doubleValue();
        return BigDecimal.valueOf(Math.pow(multiplied.doubleValue(), n));
    }
}
