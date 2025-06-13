FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw && ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests

COPY entrypoint.sh .
RUN chmod +x entrypoint.sh

EXPOSE 8080

# --- ELIMINAR ESTA LÍNEA ---
# ENV JAVA_OPTS="-Xms32m -Xmx200m -XX:MaxMetaspaceSize=64m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:+UseStringDeduplication -XX:+UseCompressedOops -Djava.security.egd=file:/dev/./urandom"
# ---------------------------

ENTRYPOINT ["./entrypoint.sh"]