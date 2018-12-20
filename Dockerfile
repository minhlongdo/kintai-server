FROM openjdk:8u171-alpine3.7
RUN apk --no-cache add curl
COPY build/libs/*-all.jar kintai-server.jar
CMD java ${JAVA_OPTS} -jar kintai-server.jar