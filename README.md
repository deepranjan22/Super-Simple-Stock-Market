# Super-Simple-Stock-Market
Requirements 1. Provide working source code that will :- a. For a given stock, i. Given any price as input, calculate the dividend yield ii. Given any price as input, calculate the P/E Ratio iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and traded price iv. Calculate Volume Weighted Stock Price based on trades in past 15 minutes b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks  Constraints &amp; Notes 1. Written in one of these languages:  Java, C#, C++, Python 2. No database or GUI is required, all data need only be held in memory 3. No prior knowledge of stock markets or trading is required – all formulas are provided below. Table1. Sample data from the Global Beverage Corporation Exchange Stock Symbol  Type Last Dividend  Fixed Dividend  Par Value TEA Common 0 100 POP Common 8 100 ALE Common 23 60 GIN Preferred 8 2% 100 JOE Common 13 250 All number values in pennies Table 2. Formula  Common Preferred  Dividend Yield Last Dividend Price  Fixed Dividend . Par Value  Price  P/E Ratio Price Dividend Geometric Mean √p1p2p3 ... pn  n  Volume Weighted Stock Price ∑i Traded Pricei × Quantityi ∑i Quantityi

---------------------------------------------Steps to run the project -----------------------------------------------
checkout the repository and use the comand mvn clean install to build the artifactory.
Once step 3 is sucessfylly executed, Run the StockCalculatorApp class as main class and provide the valid input on the console.
the program will calculate the required output.

----------------------------------------------------------------------------------------------------------------------
