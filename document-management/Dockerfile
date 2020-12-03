# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="bhawna.goswami2@globallogic.com"

# Add a volume pointing to /tmp
#VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 80
#RUN mkdir disco
COPY disco .
#COPY disco-java-agent.jar ./disco/disco-java-agent.jar
#COPY aws-xray-agent-plugin.jar ./disco/aws-xray-agent-plugin.jar
#COPY disco-java-agent-aws-plugin.jar ./disco/disco-java-agent-aws-plugin.jar
#COPY disco-java-agent-sql-plugin.jar ./disco/disco-java-agent-sql-plugin.jar
#COPY disco-java-agent-web-plugin.jar ./disco/disco-java-agent-web-plugin.jar

# The application's jar file
ARG JAR_FILE=target/document-management-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} document-management-0.0.1-SNAPSHOT.jar

# Run the jar file 
#ENTRYPOINT ["java","-jar","document-management-0.0.1-SNAPSHOT.jar","-javaagent:D:/Softwares/disco/disco-java-agent.jar=pluginPath=/D:/Softwares/disco/disco-plugins"]
CMD ["java","-javaagent:./disco-java-agent.jar=pluginPath=./disco-plugins","-jar","document-management-0.0.1-SNAPSHOT.jar"]