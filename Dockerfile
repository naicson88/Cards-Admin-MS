FROM openjdk:11
COPY target/adminMS-0.0.1-SNAPSHOT.jar Yugioh-Admin.jar
RUN echo "America/Fortaleza" > /etc/timezone
ENTRYPOINT ["java","-jar","/Yugioh-Admin.jar"]

#docker build --tag=yugioh-admin:latest . <- dont forget the dot
#docker run --name yugioh-admin  -p8081:8081 yugioh-admin:latest