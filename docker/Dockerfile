
FROM bellsoft/liberica-openjdk-alpine:21
LABEL org.opencontainers.image.authors="Danil"
ARG JAR_FILE=PizzaBot-0.1.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]