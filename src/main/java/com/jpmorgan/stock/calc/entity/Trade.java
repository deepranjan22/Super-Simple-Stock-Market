package com.jpmorgan.stock.calc.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Trade {
    private final long id;
    private final TradeIndicator indicator;
    private final BigDecimal tradedPrice;
    private final long sharesQuantity;
    private final LocalDateTime timestamp;

    public Trade(long id, TradeIndicator indicator, BigDecimal tradedPrice, long sharesQuantity) {
        this.id = id;
        this.indicator = indicator;
        this.tradedPrice = tradedPrice;
        this.sharesQuantity = sharesQuantity;
        this.timestamp = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public TradeIndicator getIndicator() {
        return indicator;
    }

    public BigDecimal getTradedPrice() {
        return tradedPrice;
    }

    public long getSharesQuantity() {
        return sharesQuantity;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trade)) return false;
        Trade trade = (Trade) o;
        return getId() == trade.getId() &&
                getSharesQuantity() == trade.getSharesQuantity() &&
                getIndicator() == trade.getIndicator() &&
                Objects.equals(getTradedPrice(), trade.getTradedPrice()) &&
                Objects.equals(getTimestamp(), trade.getTimestamp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIndicator(), getTradedPrice(), getSharesQuantity(), getTimestamp());
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", indicator=" + indicator +
                ", tradedPrice=" + tradedPrice +
                ", sharesQuantity=" + sharesQuantity +
                ", timestamp=" + timestamp +
                '}';
    }
}
