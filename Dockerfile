FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

# JVM optimizada para 250MB máximo
ENV JAVA_OPTS="-Xms32m -Xmx200m -XX:MaxMetaspaceSize=64m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+UseStringDeduplication -XX:+UseCompressedOops -Djava.security.egd=file:/dev/./urandom"

CMD ["sh", "-c", "java $JAVA_OPTS -jar target/EcoSurprise-0.0.1-SNAPSHOT.jar"]