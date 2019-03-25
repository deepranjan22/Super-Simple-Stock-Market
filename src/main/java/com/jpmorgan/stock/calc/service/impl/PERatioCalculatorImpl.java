package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.service.api.PERatioCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class PERatioCalculatorImpl implements PERatioCalculatorService {

    @Override
    public BigDecimal calculate(BigDecimal dividend, BigDecimal price) {
        Objects.requireNonNull(dividend, "Dividend can't be 'Null'");
        Objects.requireNonNull(price, "Price can't be 'Null'");

        if(dividend.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return price.divide(dividend, DEFAULT_MATH_CONTEXT);
    }
}
