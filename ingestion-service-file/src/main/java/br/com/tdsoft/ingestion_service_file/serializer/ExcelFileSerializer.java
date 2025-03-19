package br.com.tdsoft.ingestion_service_file.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExcelFileSerializer {

    private final ObjectMapper objectMapper;
    private final Logger LOG = LoggerFactory.getLogger(ExcelFileSerializer.class);

    @Autowired
    public ExcelFileSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public byte[] serializeExcelFileToByte(String excelData){
        try{
            return objectMapper.writeValueAsBytes(excelData);
        }catch (Exception e){
            LOG.debug("error with serialize to byte[] generates a follow error", e);
            throw new RuntimeException(e);
        }
    }

    public String serializeExcelFile(String excelData){
        try{
            return objectMapper.writeValueAsString(excelData);
        }catch (Exception e){
            LOG.debug("error with serialize to byte[] generates a follow error", e);
            return null;
        }
    }
}
