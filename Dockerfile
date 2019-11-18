FROM maven:3.5.4-jdk-9 as build

WORKDIR /k8sdemo

# Create a first layer to cache the "Maven World" in the local repository.
# Incremental docker builds will always resume after that, unless you update
# the pom
ADD pom.xml .
RUN mvn package -DskipTests

# Do the Maven build!
# Incremental docker builds will resume here when you change sources
ADD src src
RUN mvn package -DskipTests
RUN echo "done!"

# 2nd stage, build the runtime image
FROM openjdk:8-jre-slim
WORKDIR /k8sdemo

# Copy the binary built in the 1st stage
COPY --from=build /k8sdemo/target/dbs-0.0.1-SNAPSHOT.war ./
#COPY --from=build /k8sdemo/target/libs ./libs

CMD ["java", "-jar", "dbs-0.0.1-SNAPSHOT.war"]

EXPOSE 8080
