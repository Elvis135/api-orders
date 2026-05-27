FROM eclipse-temurin:17-jre

ENV APP_HOME=/api-orders-app

RUN mkdir -p $APP_HOME

WORKDIR $APP_HOME

EXPOSE 8080

COPY target/api-orders-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]