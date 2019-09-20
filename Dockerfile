FROM openjdk:11
USER root
WORKDIR /root
VOLUME /tmp /tmp
VOLUME /root/async-profiler /root/async-profiler
COPY my.jar my.jar
EXPOSE 8080
CMD ["sh", "-c", "java ${MY_ARGS} -jar my.jar"]
