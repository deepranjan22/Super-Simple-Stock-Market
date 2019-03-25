package com.jpmorgan.stock.calc.repo;

import com.jpmorgan.stock.calc.entity.Trade;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class TradeRepositoryImpl implements TradeRepository {

    private final Map<Long, Trade> REPOSITORY = new ConcurrentHashMap<>();

    @Override
    public long count() {
        return REPOSITORY.values().size();
    }

    @Override
    public Trade create(Trade value) {
        REPOSITORY.put(value.getId(), value);
        return REPOSITORY.get(value.getId());
    }

    @Override
    public List<Trade> getAll() {
        return new ArrayList<>(REPOSITORY.values());
    }

    @Override
    public List<Trade> lastMinutes(final int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Minutes is negative value");
        }

        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime difference = now.minusMinutes(minutes);

        return getAll()
                .stream()
                .filter(entity -> isBeforeOrEqual(difference, entity.getTimestamp()))
                .collect(Collectors.toList());
    }

    protected boolean isBeforeOrEqual(final LocalDateTime difference, final LocalDateTime timestamp) {
        return difference.isBefore(timestamp) || difference.isEqual(timestamp);
    }
}
