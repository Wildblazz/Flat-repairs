FROM maven:3.8.4-jdk-11-slim AS build
WORKDIR '/app'
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src/ /app/src/
RUN mvn clean package
ARG DEPENDENCY=/app/target/dependency
RUN mkdir -p ${DEPENDENCY} && (cd ${DEPENDENCY}; jar -xf ../*.jar)


FROM openjdk:11.0.13 as runnable
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java", "-Dspring.profiles.active=${ACTIVE_PROFILE}", "-cp","app:app/lib/*","com.lpr.repairs.Application"]
