package br.com.tdsoft.ingestion_service_file.kafka;

import br.com.tdsoft.ingestion_service_file.reader.ExcelReader;
import br.com.tdsoft.ingestion_service_file.serializer.ExcelFileSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessages {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final ExcelFileSerializer excelFileSerializer;
    private final ExcelReader excelReader;
    private final Logger LOG = LoggerFactory.getLogger(KafkaMessages.class);

    @Value("${kafka.topic.name}")
    String topic;

    @Autowired
    public KafkaMessages(KafkaTemplate<String, byte[]> kafkaTemplate,
                         ExcelFileSerializer excelFileSerializer,
                         ExcelReader excelReader) {
        this.kafkaTemplate = kafkaTemplate;
        this.excelFileSerializer = excelFileSerializer;
        this.excelReader = excelReader;
    }

    //topic data-file
    public void sendDataToKafka(){
        try{
            byte[] excelData  = this.excelFileSerializer.serializeExcelFileToByte(excelReader.firstSheet().toString());
            kafkaTemplate.send(topic, excelData);
        }catch (Exception e){
            LOG.debug("error with serialize to byte[] generates a follow error", e);
            throw new RuntimeException(e);
        }
    }


}
