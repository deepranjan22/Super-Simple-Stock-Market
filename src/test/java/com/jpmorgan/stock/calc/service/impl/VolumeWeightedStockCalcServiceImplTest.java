package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.StockCalculatorApp;
import com.jpmorgan.stock.calc.entity.Trade;
import com.jpmorgan.stock.calc.entity.TradeIndicator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {StockCalculatorApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class VolumeWeightedStockCalcServiceImplTest {

    @Autowired
    private VolumeWeightedStockCalcServiceImpl volumeWeightedStockCalcService;

    @Test
    public void shouldReturnZeroIfListIsEmpty() {
        final BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual=
                volumeWeightedStockCalcService.calculate(Collections.emptyList());
        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturnZeroIfListIsNull() {
        final BigDecimal expected = BigDecimal.ZERO;
        BigDecimal actual=
                volumeWeightedStockCalcService.calculate(null);
        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturn100AfterCalc() {
        BigDecimal expected = BigDecimal.valueOf(100);
        Trade trade1 = new Trade(1, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        Trade trade2 = new Trade(2, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        Trade trade3 = new Trade(3, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);

        List<Trade> trades = Arrays.asList(trade1, trade2, trade3);
        BigDecimal actual = volumeWeightedStockCalcService.calculate(trades).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(actual.compareTo(expected) == 0);
    }

    @Test
    public void shouldReturn26252AfterCalc() {
        BigDecimal expected = BigDecimal.valueOf(300.00);
        Trade trade1 = new Trade(1, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        Trade trade2 = new Trade(2, TradeIndicator.BUY, BigDecimal.valueOf(200), 1);
        Trade trade3 = new Trade(3, TradeIndicator.BUY, BigDecimal.valueOf(300), 1);
        Trade trade4 = new Trade(4, TradeIndicator.BUY, BigDecimal.valueOf(400), 1);
        Trade trade5 = new Trade(5, TradeIndicator.BUY, BigDecimal.valueOf(500), 1);

        List<Trade> trades = Arrays.asList(trade1, trade2, trade3, trade4, trade5);
        BigDecimal actual = volumeWeightedStockCalcService.calculate(trades).setScale(2, BigDecimal.ROUND_HALF_EVEN);

        assertTrue(actual.compareTo(expected) == 0);
    }
}
