# Start with a base image containing Java runtime
FROM openjdk:8

# Add Maintainer Info
LABEL maintainer="bhawna.goswami2@globallogic.com"

# Add a volume pointing to /tmp
#VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 80
ADD disco-java-agent.jar disco/disco-java-agent.jar
ADD disco-plugins disco/disco-plugins
# The application's jar file
ARG JAR_FILE=target/document-data-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} document-data-0.0.1-SNAPSHOT.jar

# Run the jar file 
#CMD ["java","-javaagent:./disco-java-agent.jar=pluginPath=./disco-plugins"]
ENTRYPOINT ["java","-javaagent:disco/disco-java-agent.jar=pluginPath=disco/disco-plugins","-jar","document-data-0.0.1-SNAPSHOT.jar"]
