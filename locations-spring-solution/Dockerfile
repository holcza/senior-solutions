#FROM adoptopenjdk:14-jre-hotspot
#RUN mkdir /opt/app
#COPY target/locations-spring-solution-0.0.1-SNAPSHOT.jar /opt/app/locations.jar
#CMD ["java", "-jar", "/opt/app/locations.jar"]

FROM adoptopenjdk:14-jre-hotspot as builder
WORKDIR application
COPY target/locations-spring-solution-0.0.1-SNAPSHOT.jar locations-layered.jar
RUN java -Djarmode=layertools -jar locations-layered.jar extract

FROM adoptopenjdk:14-jre-hotspot
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT["java",\
"org.springframework.boot.loader.JarLauncher"]