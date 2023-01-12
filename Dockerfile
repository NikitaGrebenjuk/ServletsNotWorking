#FROM maven:3.6.3-jdk-11 AS build

# Copy the Maven project
#COPY . /usr/src/mywebapp

# Build the Maven project
#RUN mvn -f /usr/src/mywebapp/pom.xml clean package

#
# Production stage
#
#FROM tomcat:11.0-jre11
FROM tomcat:10.1.1-jre11
#alternative1:
COPY export/*.war /usr/local/tomcat/webapps/

# Copy the war file to the Tomcat webapps directory
# COPY --from=build /usr/src/mywebapp/target/*.war /usr/local/tomcat/webapps/

# Start the Tomcat server
# CMD ["catalina.sh", "run"]