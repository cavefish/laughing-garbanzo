FROM maven:3.9.9-amazoncorretto-21

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package spring-boot:repackage

ENTRYPOINT ["mvn", "spring-boot:run"]