package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.StockCalculatorApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {StockCalculatorApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class PERatioCalculatorImplTest {

    @Autowired
    private PERatioCalculatorImpl peRatioCalculator;

    @Test
    public void shouldReturnZEROAfterCalculation() {
        final BigDecimal expected = BigDecimal.ZERO;
        final BigDecimal dividend = BigDecimal.TEN;
        final BigDecimal price = BigDecimal.ZERO;

        BigDecimal actual = peRatioCalculator.calculate(dividend, price);
        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturnOneAfterCalculation() {
        final BigDecimal expected = BigDecimal.ONE;
        final BigDecimal dividend = BigDecimal.TEN;
        final BigDecimal price = BigDecimal.TEN;

        BigDecimal actual = peRatioCalculator.calculate(dividend, price);
        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturn004AfterCalculation() {
        final BigDecimal expected = BigDecimal.valueOf(0.004);
        final BigDecimal dividend = BigDecimal.valueOf(500);
        final BigDecimal price = BigDecimal.valueOf(2);

        BigDecimal actual = peRatioCalculator.calculate(dividend, price);
        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfDividendIsNull() {
        peRatioCalculator.calculate(null, BigDecimal.ONE);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfPriceIsNull() {
        peRatioCalculator.calculate(BigDecimal.ONE, null);
    }
}
