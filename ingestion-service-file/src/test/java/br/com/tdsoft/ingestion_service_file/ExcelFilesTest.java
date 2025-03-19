package br.com.tdsoft.ingestion_service_file;

import br.com.tdsoft.ingestion_service_file.reader.ExcelReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class ExcelFilesTest {

    private ExcelReader excelReader;

    @BeforeEach
    void setUp() {
        excelReader = new ExcelReader();
    }

    @Test
    public void getDataFromExcelFile() {
        Map<Integer, List<String>> data = excelReader.firstSheet();

        Assertions.assertFalse(data.isEmpty());
        Assertions.assertFalse(data.get(0).isEmpty());

    }
}
