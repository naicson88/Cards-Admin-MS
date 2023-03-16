FROM adoptopenjdk/openjdk11:alpine
COPY target/*.jar cards-admin.jar
RUN echo "America/Fortaleza" > /etc/timezone
EXPOSE 8081

ENTRYPOINT ["java", "-DLOCAL_IP=192.168.1.8", "-jar","/cards-admin.jar"]

#docker build -t cards-main . <- dont forget the dot
#docker run -it -p 8081:8081 cards-main
#docker run --name cards-gateway -e "SPRING_PROFILES_ACTIVE=prod"  -p8083:8083 cards-gateway:latest
#mvn -DLOCAL_IP=192.168.1.7 clean package