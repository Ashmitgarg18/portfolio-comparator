package com.ash.projects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ExcelReader {
    public Map<String, Double> readTradeBook(String path){

        Map<String, Double> dateWiseAmountSpent = new TreeMap<>();

        try {

            FileInputStream file = new FileInputStream(path);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();

                String date = "";
                String tradeType = "";
                double quantity = 0;
                double price = 0;
                int cellIndex = 0;

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    if (cellIndex == 2 || cellIndex == 6 || cellIndex == 8 || cellIndex == 9) {
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                if(cellIndex == 8){
                                    quantity = cell.getNumericCellValue();
                                }
                                if(cellIndex == 9){
                                    price = cell.getNumericCellValue();
                                }
                                break;
                            case STRING:
                                if(cellIndex == 2){
                                    date = cell.getStringCellValue();
                                }
                                if(cellIndex == 6){
                                    tradeType = cell.getStringCellValue();
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    cellIndex++;
                }
                double moneySpent = quantity * price;
                if(tradeType.equalsIgnoreCase("buy")){
                    dateWiseAmountSpent.put(date, dateWiseAmountSpent.getOrDefault(date, 0.0) + moneySpent);
                }
                else if (tradeType.equalsIgnoreCase("sell")){
                    dateWiseAmountSpent.put(date, dateWiseAmountSpent.getOrDefault(date, 0.0) - moneySpent);
                }
            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateWiseAmountSpent;
    }
}
