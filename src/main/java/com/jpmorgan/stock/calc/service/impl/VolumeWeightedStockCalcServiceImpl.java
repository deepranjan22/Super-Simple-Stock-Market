package com.jpmorgan.stock.calc.service.impl;

import com.jpmorgan.stock.calc.entity.Trade;
import com.jpmorgan.stock.calc.service.api.VolumeWeightedStockCalcService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class VolumeWeightedStockCalcServiceImpl implements VolumeWeightedStockCalcService {
    private static final Logger logger = Logger.getLogger(VolumeWeightedStockCalcServiceImpl.class);

    @Override
    public BigDecimal calculate(List<Trade> trades) {
        if (Objects.isNull(trades) || trades.isEmpty()) {
            logger.warn("Trades are empty");
            return BigDecimal.ZERO;
        }

        BigDecimal sumTradedPrice = BigDecimal.ZERO;
        long sumQuantity = 0;

        for (Trade trade : trades) {
            final BigDecimal tradedPrice = trade.getTradedPrice();
            final long sharesQuantity = trade.getSharesQuantity();

            sumTradedPrice = sumTradedPrice.add(tradedPrice.multiply(BigDecimal.valueOf(sharesQuantity)));
            sumQuantity += sharesQuantity;
        }

        return sumTradedPrice.divide(BigDecimal.valueOf(sumQuantity), DEFAULT_MATH_CONTEXT);
    }
}
