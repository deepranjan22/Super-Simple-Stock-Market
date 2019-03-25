package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.StockCalculatorApp;
import com.jpmorgan.stock.calc.facts.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {StockCalculatorApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class DividendYieldCalculatorImplTest {

    @Autowired
    private DividendYieldCalculatorImpl dividendYieldCalculator;

    @Test
    public void calculateCommonDividendYieldTest() {
        final BigDecimal expected = BigDecimal.valueOf(0.23);
        final BigDecimal price = BigDecimal.valueOf(100);

        Stock stock = Stock.ALE;
        BigDecimal commonDividendYield =
                dividendYieldCalculator.calculateCommonDividendYield(stock, price);

        assertTrue(commonDividendYield.compareTo(expected) == 0);
    }

    @Test
    public void calculatePreferredDividendYieldTest() {
        final BigDecimal expected = BigDecimal.valueOf(0.0533333);
        final BigDecimal price = BigDecimal.valueOf(150);

        Stock stock = Stock.GIN;
        BigDecimal preferredDividendYield =
                dividendYieldCalculator.calculateCommonDividendYield(stock, price);

        assertTrue(preferredDividendYield.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturn08AfterEvaluation() {
        final BigDecimal expected = BigDecimal.valueOf(0.8);
        final BigDecimal price = BigDecimal.valueOf(10);

        Stock stock = Stock.GIN;
        BigDecimal commonDividendYield =
                dividendYieldCalculator.calculateCommonDividendYield(stock, price);

        assertTrue(commonDividendYield.compareTo(expected) == 0);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfStockIsNull() {
        final BigDecimal price = BigDecimal.TEN;

        dividendYieldCalculator.calculate(null, price);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionIfPriceIsNull() {
        Stock stock = Stock.GIN;
        dividendYieldCalculator.calculate(stock, null);
    }
}
