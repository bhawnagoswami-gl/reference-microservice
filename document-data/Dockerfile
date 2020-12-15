# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="bhawna.goswami2@globallogic.com"

# Add a volume pointing to /tmp
#VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 80

# The application's jar file
ADD disco-java-agent.jar disco-java-agent.jar
ADD disco-plugins/aws-xray-agent-plugin.jar disco-plugins/aws-xray-agent-plugin.jar
ADD disco-plugins/disco-java-agent-aws-plugin.jar disco-plugins/disco-java-agent-aws-plugin.jar
ADD disco-plugins/disco-java-agent-sql-plugin.jar disco-plugins/disco-java-agent-sql-plugin.jar
ADD disco-plugins/disco-java-agent-web-plugin.jar disco-plugins/disco-java-agent-web-plugin.jar

ARG JAR_FILE=target/document-data-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} document-data-0.0.1-SNAPSHOT.jar

# Run the jar file 
#CMD ["java","-javaagent:disco-java-agent.jar=pluginPath=/disco-plugins"]
ENTRYPOINT ["java","-javaagent:disco-java-agent.jar=pluginPath=/disco-plugins","-jar","document-data-0.0.1-SNAPSHOT.jar"]
