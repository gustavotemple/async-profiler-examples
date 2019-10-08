# async-profiler-examples

## Build

Run (https://stackoverflow.com/a/52404325):
```console
$./gradlew bootJar
```

## Async Profiler files

[setup_profiler.sh](setup_profiler.sh)

```sh
#!/bin/sh
if [ "$(whoami)" != "root" ]; then exit 1; fi
sh -c "echo 1 > /proc/sys/kernel/perf_event_paranoid"
sh -c "echo 0 > /proc/sys/kernel/kptr_restrict"
cd /root
if [ ! -d "async-profiler" ]; then git clone https://github.com/jvm-profiling-tools/async-profiler.git; fi
make -C async-profiler
async-profiler/profiler.sh --version
```

Run:
```console
sudo sh ./setup_profiler.sh
```

[Dockerfile](Dockerfile)

```Dockerfile
FROM openjdk:11
USER root
WORKDIR /root
VOLUME /tmp /tmp
VOLUME /root/async-profiler /root/async-profiler
COPY my.jar my.jar
EXPOSE 8080
CMD ["sh", "-c", "java ${MY_ARGS} -jar my.jar"]
```

[docker-compose.yml](docker-compose.yml)

```yml
version: '3'
services:
  app:
    build:
      context: .
    ports:
      - "8080:8080"
    volumes:
      - /tmp:/tmp
      - /root/async-profiler:/root/async-profiler
    security_opt:
      - seccomp:unconfined
    environment:
      - MY_ARGS=-XX:+PreserveFramePointer -XX:+UnlockDiagnosticVMOptions -XX:+DebugNonSafepoints
```

Run:

```console
docker swarm init
docker build -t app -f Dockerfile .
docker stack deploy --compose-file docker-compose.yml test
docker stats
```

Run:

```console
sudo ps -ef | grep "java" | grep "PreserveFramePointer" \
 | grep -v "sbt.ForkMain" | grep -v "grep" \
 | awk '{print $2}' \
 | sudo xargs -I pid /root/async-profiler/profiler.sh -d 60 -f /tmp/title.svg --title title -e itimer pid
```

## Gatling Project

- https://github.com/gustavotemple/gatling-sbt-plugin-demo

AsyncProfiler call:

- https://github.com/gustavotemple/gatling-sbt-plugin-demo/blob/master/src/test/scala/gatling/GatlingTest.scala#L66

Debug Gatling:

- https://github.com/gustavotemple/gatling-sbt-plugin-demo/blob/master/src/test/scala/gatling/GatlingTest.scala#L14
- https://stackoverflow.com/a/30620194

## Notes

- async-profiler should be run from the host by a privileged user.
- Make sure that the target container can access `libasyncProfiler.so` by the same absolute path as on the host.
- `/tmp` directory of Java process should be physically the same directory as `/tmp` of your shell.
- Some JVMs do not provide attach mode. The attach mode is supported for e.g. Oracle JDK, OpenJDK HotSpot, and IBM JDK based on OpenJ9.
- It is possible to profile a JVM running in a Docker from inside its container and from its host system.
