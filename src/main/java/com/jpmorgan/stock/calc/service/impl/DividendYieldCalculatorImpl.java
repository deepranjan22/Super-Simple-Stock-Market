package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.facts.Stock;
import com.jpmorgan.stock.calc.facts.StockType;
import com.jpmorgan.stock.calc.service.api.DividendYieldCalculatorService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class DividendYieldCalculatorImpl implements DividendYieldCalculatorService {
    private static final Logger logger = Logger.getLogger(DividendYieldCalculatorImpl.class);

    @Override
    public BigDecimal calculate(final Stock stock, final BigDecimal price) {
        Objects.requireNonNull(stock, "Stock can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");

        logger.info("Processing Stock: " + stock);
        if(stock.getType().equals(StockType.COMMON.getName())) {
            return calculateCommonDividendYield(stock, price);
        } else {
            return calculatePreferredDividendYield(stock, price);
        }
    }

    protected BigDecimal calculateCommonDividendYield(Stock stock, BigDecimal price) {
        BigDecimal lastDividend = stock.getLastDividend();

        Objects.requireNonNull(lastDividend, "Last Dividend can't be 'Null'");
        return lastDividend.divide(price, DEFAULT_MATH_CONTEXT);
    }

    protected BigDecimal calculatePreferredDividendYield(Stock stock, BigDecimal price) {
        BigDecimal fixedDividend = stock.getFixedDividend();
        BigDecimal parValue = stock.getParValue();

        Objects.requireNonNull(fixedDividend, "Fixed Dividend can't be 'Null'");
        Objects.requireNonNull(parValue, "Par Value can't be 'Null'");

        return fixedDividend
                .multiply(parValue)
                .divide(price.multiply(BigDecimal.valueOf(100)), DEFAULT_MATH_CONTEXT);
    }
}
