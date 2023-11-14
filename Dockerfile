FROM maven:3-openjdk-17-slim
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn package
ENTRYPOINT java -Dspring.datasource.url=jdbc:postgresql://postgres:5432/todo -jar ./target/todo-1.0.jar
