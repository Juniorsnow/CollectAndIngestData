package br.com.tdsoft.ingestion_service_file.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ExcelReader {

    private final Logger LOG = LoggerFactory.getLogger(ExcelReader.class);

    public Map<Integer, List<String>> firstSheet() {
        try (InputStream file = getClass().getClassLoader().getResourceAsStream("Grade Janeiro_GSP_SANTOS_2024.xlsx")) {
            if (file == null) {
                LOG.debug("File not found");
                throw new FileNotFoundException("File not found");
            }
            try (Workbook workbook = new XSSFWorkbook(file)) {
                Sheet sheet = workbook.getSheetAt(0);

                Map<Integer, List<String>> data = new HashMap<>();

                int i = 0;

                for (Row row : sheet) {
                    data.put(i, new ArrayList<>());

                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                                data.get(i).add(cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                data.get(i).add(String.valueOf(cell.getNumericCellValue()));
                                break;
                            case BOOLEAN:
                                data.get(i).add(String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case BLANK:
                                data.get(i).add("");
                                break;
                            case ERROR:
                                data.get(i).add("Error with cell");

                            default:
                                data.get(i).add("Format not supported");
                                break;
                        }
                    }
                    i++;
                }
                return data;
            }

        } catch (Exception e) {
            LOG.debug("An exception occurred while reading file");
            throw new RuntimeException(e);
        }

    }

}
