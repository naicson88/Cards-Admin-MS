FROM adoptopenjdk/openjdk11:alpine
COPY target/*.jar cards-admin.jar
RUN echo "America/Fortaleza" > /etc/timezone

#Permite a comunicação com o container
RUN usermod -a -G root jenkins
RUN chmod 777 /var/run/docker.sock 
#ENTRYPOINT ["java", "-jar","/cards-gateway.jar"]
ENTRYPOINT ["java", "-DLOCAL_IP=192.168.1.8", "-jar","/cards-admin.jar"]

#docker build --tag=cards-gateway:latest . <- dont forget the dot
#docker run --name cards-gateway -e "SPRING_PROFILES_ACTIVE=prod"  -p8083:8083 cards-gateway:latest
#mvn -DLOCAL_IP=192.168.1.7 clean package