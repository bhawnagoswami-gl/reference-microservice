# reference-microservice
To run your service in local just replace the below lines in application.properties file
for document-data service :
spring.datasource.driver-class-name=com.amazonaws.secretsmanager.sql.AWSSecretsManagerMySQLDriver
spring.datasource.url=jdbc-secretsmanager:mysql://localhost:3306/documentdata
spring.datasource.username=/secrets/localdocdata/db

for metainfo-data
spring.datasource.driver-class-name:com.amazonaws.secretsmanager.sql.AWSSecretsManagerMySQLDriver
spring.datasource.url=jdbc-secretsmanager:mysql://localhost:3306/metadata
spring.datasource.username=/secrets/localmetadata/db

and provide the port numbers to services and replace the urls in doc-management accordingly like :
endpoint.docinfoUrl=http://localhost:8080
endpoint.metainfoUrl=http://localhost:8081
