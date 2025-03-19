Projeto: Data Ingestion com Apache Camel e Kafka(Java 21) 
Descrição
Este é um projeto de um sistema de ingestão de dados que coleta informações de diferentes fontes, como: 
Banco de dados, planilhas de Excel, e arquivos JSON, envia os dados para um cluster Kafka. Os dados são consumidos e processados pelo Apache Camel. 
Arquitetura
O Projeto é composto por três aplicações (producers) e uma que consome (consumer)
Aplicações Produtoras
Aplicação 1: ingestion-service-db  
Banco de Dados H2 em memória. 
Lê dados de um banco de dados H2.
Publica os dados no Kafka no tópico data-db.

Aplicação 2: ingestion-service-file
Processa arquivos Excel. 
Publica os dados no Kafka no topico data-file.

Aplicação 3: ingestion-service-webformat
Processa arquivos JSON. 
Publica os dados no Kafka no topico data-webformat.
Cada aplicação possui os seguintes componentes:
Cada aplicação possui os seguintes componentes. 
KafkaMessages: Responsável pelo envio de mensagens ao Kafka.
KafkaProducerConfig: Configuração de serialização das mensagens no Kafka (binary object).
Serializer: Responsável por obter o valor dos objetos que serão enviados ao Kafka e serializer em byte[].

Kafka
O cluster Kafka contém três tópicos principais:
data-db,
data-file,
data-webformat,
Cada tópico pode ter várias partições para balanceamento de carga e escalabilidade.

Aplicação Consumidora (Apache Camel)
Consome mensagems dos topicos do Kafka e processa os dados. 
Contém rotas Camel para: 
FromBroker: Consome diretamente do Kafka. 
FromControllerApplicationProducer: Envia manualmente os dados para o Kafka chamando um end-point a partir das aplicações produtoras(producers) e então consome os dados do broker. 
Tecnologias Utilizadas

Tecnologias Utilizadas
Spring Boot, Apache Camel, Kafka, H2 Database.
Apache POI (para leitura de planilhas Excel).
JSON/XML Parsers.

As aplicações producers rodam em: 8080,8081,8082.
A aplicação consumer roda em: 8083. 
O controller da aplicação consumidora funciona da seguinte forma;
Ex: http://localhost:8083/start?routeId=ingestion-service-db-trigger&routeIdOfKafkaRoute=process-kafka-message
routeId={routeId} -> aponta para a aplicação producer, trigger&routeIdOfKafkaRoute = {routeId} -> aponta para a rota consumidora que pode ser configurada e usada para processar dados de maneira customizável, aceitando regras de negocios.  
O Kafka está configurado para rodar em 9092
A imagem do kafka para rodar no docker pode ser feito o download nesta pagina 
https://kafka.apache.org/quickstart

Estrutura do Projeto 
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




