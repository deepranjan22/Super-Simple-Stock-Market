package com.jpmorgan.stock.calc;

import com.jpmorgan.stock.calc.entity.Trade;
import com.jpmorgan.stock.calc.entity.TradeIndicator;
import com.jpmorgan.stock.calc.facts.Stock;
import com.jpmorgan.stock.calc.repo.TradeRepository;
import com.jpmorgan.stock.calc.service.api.DividendYieldCalculatorService;
import com.jpmorgan.stock.calc.service.api.GeometricMeanCalcService;
import com.jpmorgan.stock.calc.service.api.PERatioCalculatorService;
import com.jpmorgan.stock.calc.service.api.VolumeWeightedStockCalcService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StockCalcRunner {

    private static final Logger logger = Logger.getLogger(StockCalcRunner.class);
    private static final int MINUTES = 15;


    @Autowired
    private DividendYieldCalculatorService dividendYieldCalculatorService;

    @Autowired
    private PERatioCalculatorService peRatioCalculatorService;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private VolumeWeightedStockCalcService volumeWeightedStockCalcService;

    @Autowired
    private GeometricMeanCalcService geometricMeanCalcService;

    public void process(Stock stockSymbol, BigDecimal price) {

        final BigDecimal dividendYield = dividendYieldCalculatorService.calculate(stockSymbol, price);
        logger.info("Stock - Dividend Yield: " + dividendYield);

        final BigDecimal peRatio = peRatioCalculatorService.calculate(dividendYield, price);
        logger.info("Stock - P/E Ratio: " + peRatio);

        Trade trade = new Trade(System.currentTimeMillis(),
                getIndicator(peRatio),
                price,
                getSharesQuantity());

        Trade persistedTrade = tradeRepository.create(trade);
        logger.info("Recorded a trade: " + persistedTrade);

        final BigDecimal volumeWeightedStockPrice = volumeWeightedStockCalcService.calculate(tradeRepository.lastMinutes(MINUTES));
        logger.info("Stock - Volume Weighted Stock Price: " + volumeWeightedStockPrice);


        final BigDecimal geometricMean = geometricMeanCalcService.calculate(tradeRepository.getAll());
        logger.info("Stock - GBCE All Share Index: " + geometricMean);

    }

    protected TradeIndicator getIndicator(final BigDecimal peRatio) {
        return peRatio.compareTo(BigDecimal.ONE) > 0 ? TradeIndicator.BUY : TradeIndicator.SELL;
    }

    protected long getSharesQuantity() {
        final long numOfTrades = tradeRepository.count();
        return numOfTrades == 0 ? 1 : numOfTrades;
    }
}
