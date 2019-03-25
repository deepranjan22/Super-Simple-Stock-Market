package com.jpmorgan.stock.calc.service.api;

import java.math.MathContext;
import java.math.RoundingMode;

public interface StockCalcService {

    /**
     * PRECISION - The number of digits to be used for an operation; results are rounded to this precision.
     */
    int PRECISION = 6;

    /**
     * DEFAULT_MATH_CONTEXT - Immutable object which encapsulate the context settings which
     * describe certain rules for numerical operators.
     */
    MathContext DEFAULT_MATH_CONTEXT = new MathContext(PRECISION, RoundingMode.HALF_EVEN);
}
