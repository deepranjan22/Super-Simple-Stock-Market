package com.jpmorgan.stock.calc;

import com.jpmorgan.stock.calc.facts.Stock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Stock Calculator.
 * This is the main console application to perform stock calculations.
 *
 */
@ComponentScan(basePackages = "com.jpmorgan.stock.calc")
public class StockCalculatorApp
{
    private static final Logger logger = Logger.getLogger(StockCalculatorApp.class);

    @Autowired
    private StockCalcRunner stockCalcRunner;

    public static void main( String[] args ) {
        logger.info("Application starting...");
        ApplicationContext context
                = new AnnotationConfigApplicationContext(StockCalculatorApp.class);

        StockCalculatorApp stockCalculatorApp = context.getBean(StockCalculatorApp.class);

        stockCalculatorApp.start();

    }

    private void start() {
        try (Scanner in = new Scanner(System.in)) {
            logger.info("Application started");

            boolean repeat = true;

            while (repeat) {
                logger.info("Please enter Stock Symbol i.e. TEA/POP/ALE/GIN/JOE");
                Stock stockSymbol = Stock.valueOf(in.next());

                logger.info("Please enter Price");
                BigDecimal price = in.nextBigDecimal();

                stockCalcRunner.process(stockSymbol, price);

                logger.info("One more Stock? (Y/N)");
                String answer = in.next();

                if (answer.equalsIgnoreCase("N") || answer.equalsIgnoreCase("NO")) {
                    repeat = false;
                }
            }
        }
        logger.info("Application stopped");
    }

}
