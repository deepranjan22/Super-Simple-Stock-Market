package com.jpmorgan.stock.calc.repo;

import com.jpmorgan.stock.calc.StockCalculatorApp;
import com.jpmorgan.stock.calc.entity.Trade;
import com.jpmorgan.stock.calc.entity.TradeIndicator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(classes = {StockCalculatorApp.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TradeRepositoryImplTest {

    @Autowired
    private TradeRepositoryImpl tradeRepository;

    @Before
    public void initTradeRepo() {
        Trade trade1 = new Trade(1, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        Trade trade2 = new Trade(2, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        Trade trade3 = new Trade(3, TradeIndicator.BUY, BigDecimal.valueOf(100), 1);
        tradeRepository.create(trade1);
        tradeRepository.create(trade2);
        tradeRepository.create(trade3);
    }

    @Test
    public void repositoryCountTest() {
        assertNotNull(tradeRepository);
        assertTrue(tradeRepository.count() > 0);
    }

    @Test
    public void getAllTradeTest() {
        List<Trade> repositoryAll = tradeRepository.getAll();
        assertTrue(!repositoryAll.isEmpty());
    }

    @Test
    public void isBeforeOrEqualTest() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime difference = now.minusMinutes(10);

        boolean beforeOrEqual = tradeRepository.isBeforeOrEqual(difference, now);
        assertTrue(beforeOrEqual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void lastMinutesNegativeTest() {
        tradeRepository.lastMinutes(-1);
    }

    @Test
    public void lastMinutesTest() {
        LocalDateTime now = LocalDateTime.now();
        List<Trade> lastMinutesTrade = tradeRepository.lastMinutes(1);
        boolean isOutOfTimeTradeSelected = false;
        for(Trade trade : lastMinutesTrade) {
            Duration duration = Duration.between(trade.getTimestamp(), now);
            long seconds = duration.getSeconds();

            if(seconds > 60) {
                //getting wrong trade
                isOutOfTimeTradeSelected = true;
            }
        }

        assertTrue(!isOutOfTimeTradeSelected);
    }
}