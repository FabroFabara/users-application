FROM maven:3.8.3-openjdk-17 as deps

WORKDIR /opt/app

COPY pom.xml pom.xml

COPY pom.xml .

RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.6.1:go-offline

FROM maven:3.8.3-openjdk-17 as build

WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app

COPY src src

RUN mvn -B -e clean install -DskipTests=true.