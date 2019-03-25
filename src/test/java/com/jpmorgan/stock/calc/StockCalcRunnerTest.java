package com.jpmorgan.stock.calc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.jpmorgan.stock.calc.facts.Stock;
import com.jpmorgan.stock.calc.repo.TradeRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * Integration test for StockCalcRunner.
 */
@ContextConfiguration(classes = {StockCalculatorApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class StockCalcRunnerTest
{
    @Autowired
    private StockCalcRunner stockCalcRunner;

    @Autowired
    private TradeRepositoryImpl tradeRepository;

    @Test
    public void processTest() {

        stockCalcRunner.process(Stock.ALE, BigDecimal.valueOf(10));

        //Make sure one trade entry is recorded.
        assertEquals(1, tradeRepository.count());

        stockCalcRunner.process(Stock.GIN, BigDecimal.valueOf(100));
        assertEquals(2, tradeRepository.count());
    }
}
