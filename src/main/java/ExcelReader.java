import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReader {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("E:\\portfolio-comparator/tradebook-GPZ076-EQ.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Map<String, Double> dateWiseAmountSpent = new HashMap<>();

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

            for(Map.Entry<String, Double> entry: dateWiseAmountSpent.entrySet()){
                System.out.println("Date: " + entry.getKey() + " , Net Amount: Rs. " + entry.getValue());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
