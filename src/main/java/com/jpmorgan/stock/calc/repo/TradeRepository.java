package com.jpmorgan.stock.calc.repo;

import com.jpmorgan.stock.calc.entity.Trade;

import java.util.List;

public interface TradeRepository {
    /**
     * Number of trade available in repository.
     * @return count
     */
    long count();

    /**
     * Creates a trade entry in repository
     * @param value - new entity to process
     * @return - persisted entity.
     */
    Trade create(Trade value);

    /**
     * retrieves trade process in last minutes.
     * @param minutes - past minutes to consider in trade calculation
     * @return all trade process during that time.
     */
    List<Trade> lastMinutes(int minutes);

    /**
     * Retrieves all available trade in repository.
     * @return
     */
    List<Trade> getAll();
}
