FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal

RUN curl -L -o /opt/ol/wlp/usr/shared/resources/mysql-connector-java-8.0.30.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.30/mysql-connector-java-8.0.30.jar
ENV CLASSPATH=/opt/ol/wlp/usr/shared/resources/mysql-connector-java-8.0.30.jar:${CLASSPATH}

COPY --chown=1001:0 /src/main/liberty/config /config

RUN features.sh

COPY --chown=1001:0 target/*.war /config/dropins

RUN configure.sh
