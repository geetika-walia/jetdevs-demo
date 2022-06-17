FROM openjdk:8

COPY ./target/jetdevs-testtask-1.0-SNAPSHOT.jar /usr/app/
COPY ./wait-for-it.sh /usr/app/wait-for-it.sh
# RUN apt-get update && apt-get upgrade && apt-get dist-upgrade
# RUN apt-get install netcat -y

WORKDIR /usr/app

run sh -c 'touch jetdevs-testtask-1.0-SNAPSHOT.jar'

ENTRYPOINT ["sh", "wait-for-it.sh", "mysqldb"]
#ENTRYPOINT ["java", "-jar", "jetdevs-testtask-1.0-SNAPSHOT.jar"]
