#!/bin/sh
if [ "$(whoami)" != "root" ]; then exit 1; fi
sh -c "echo 1 > /proc/sys/kernel/perf_event_paranoid"
sh -c "echo 0 > /proc/sys/kernel/kptr_restrict"
cd /root
if [ ! -d "async-profiler" ]; then git clone https://github.com/jvm-profiling-tools/async-profiler.git; fi
make -C async-profiler
async-profiler/profiler.sh --version
