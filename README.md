Projeto: Data Ingestion com Apache Camel e Kafka(Java 21).
Este é um projeto de um sistema de ingestão de dados que coleta informações de diferentes fontes, como: 
Banco de dados, planilhas de Excel, e arquivos JSON, envia os dados para um cluster Kafka e os dados são consumidos e processados pelo Apache Camel. 
O Projeto é composto por três aplicações (producers) e uma que consome (consumer). <br><br>																																																																																																
Aplicações Produtoras<br><br>
Aplicação 1: ingestion-service-db <br>
Banco de Dados H2 em memória. <br>
Lê dados de um banco de dados H2.<br>
Publica os dados no Kafka no tópico data-db.<br>

Aplicação 2: ingestion-service-file<br>
Processa arquivos Excel.<br> 
Publica os dados no Kafka no topico data-file.<br>

Aplicação 3: ingestion-service-webformat<br>
Processa arquivos JSON.<br> 
Publica os dados no Kafka no topico data-webformat.<br><br>
Cada aplicação possui os seguintes componentes:<br>
KafkaMessages: Responsável pelo envio de mensagens ao Kafka.<br>
KafkaProducerConfig: Configuração de serialização das mensagens no Kafka (binary object).<br>
Serializer: Responsável por obter o valor dos objetos que serão enviados ao Kafka e serializer em byte[].<br>

Kafka<br>
O cluster Kafka contém três tópicos principais:<br>
data-db,
data-file,
data-webformat,<br>
Cada tópico pode ter várias partições para balanceamento de carga e escalabilidade.

Aplicação Consumidora (Apache Camel)<br>
Consome mensagems dos topicos do Kafka e processa os dados.<br> 
Contém rotas Camel para:<br> 
FromBroker: Consome diretamente do Kafka.<br> 
FromControllerApplicationProducer: Envia manualmente os dados para o Kafka chamando um end-point a partir das aplicações produtoras(producers) e então consome os dados do broker. <br><br>
Tecnologias Utilizadas<br>
Spring Boot, Apache Camel, Kafka, H2 Database.
Apache POI,
JSON Parsers.

As aplicações producers rodam em: 8080,8081,8082, já a aplicação consumer roda em: 8083.<br>
O controller da aplicação consumidora funciona da seguinte forma;
Ex: http://localhost:8083/start?routeId=ingestion-service-db-trigger&routeIdOfKafkaRoute=process-kafka-message
routeId={routeId} -> aponta para a aplicação producer, trigger&routeIdOfKafkaRoute = {routeId} -> aponta para a rota consumidora que pode ser configurada e usada para processar dados de maneira customizável, aceitando regras de negocios.<br>  
O Kafka está configurado para rodar em 9092.
A imagem do kafka para rodar no docker pode ser feito o download nesta pagina 
https://kafka.apache.org/quickstart<br>
Escopo Inicial: <br>
![UML Diagram](https://github.com/user-attachments/assets/9a188b57-60de-45b9-ab2e-1434bce10d38)


Estrutura final do projeto: 
![UML Diagram (1)](https://github.com/user-attachments/assets/0010dd89-8ec3-4fab-bd82-33a3fb175bd4)


Fontes usadas para criação do projeto: 
https://www.baeldung.com/apache-kafka,
https://www.baeldung.com/kafka-topics-partitions,
https://www.baeldung.com/spring-kafka, 
https://camel.apache.org/components/4.8.x/kafka-component.html,
https://www.baeldung.com/camel-integration-patterns
https://www.baeldung.com/apache-camel-intro,
https://www.baeldung.com/java-microsoft-excel,
https://docs.redhat.com/en/documentation/red_hat_fuse/7.1/html/apache_camel_component_reference/kafka-component#producing_messages_to_kafka.




