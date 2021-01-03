FROM openjdk:8-jdk-alpine

WORKDIR /usr/app

COPY ./target/product-comparison-service-1.0.0.jar /usr/app/product-comparison-service-1.0.0.jar

CMD java -jar product-comparison-service-1.0.0.jar
