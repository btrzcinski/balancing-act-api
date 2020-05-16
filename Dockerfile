FROM adoptopenjdk:11-jre-hotspot
RUN mkdir /opt/app
COPY balancing-act-api-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/balancing-act-api-0.0.1-SNAPSHOT.jar"]

