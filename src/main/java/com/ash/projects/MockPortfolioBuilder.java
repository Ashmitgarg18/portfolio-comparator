//package com.ash.projects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class MockPortfolio {
//
//    @Autowired
//    private ExcelReader excelReader;
//
//    @Autowired
//    private AVNiftyBeesService avNiftyBeesService;
//
//    public void makeMockPortfolio(){
//
//        Map<String, Double> datesAndTrades = excelReader.readTradeBook("E:\\portfolio-comparator/tradebook-GPZ076-EQ.xlsx");
//
//        for(Map.Entry<String, Double> entry: datesAndTrades.entrySet()){
//            System.out.println(avNiftyBeesService.calculateNiftyBeesPriceOnDate(entry.getKey()));
//        }
//
//    }
//
//}

package com.ash.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MockPortfolioBuilder {

    @Autowired
    private ExcelReader excelReader;

    @Autowired
    private AVNiftyBeesService avNiftyBeesService;

    public void makeMockPortfolio() {

        int numberOfNiftyBeesUnits = 0;

        Map<String, Double> datesAndTrades = excelReader.readTradeBook("E:\\portfolio-comparator/tradebook-GPZ076-EQ.xlsx");

        for (Map.Entry<String, Double> entry : datesAndTrades.entrySet()) {

            double closingPrice = avNiftyBeesService.calculateNiftyBeesPriceOnDate(entry.getKey());

            int numberOfUnitsBoughtOnThatDay = (int) (entry.getValue()/closingPrice);

            numberOfNiftyBeesUnits = numberOfNiftyBeesUnits + numberOfUnitsBoughtOnThatDay;

        }

        System.out.println(numberOfNiftyBeesUnits);

    }
}