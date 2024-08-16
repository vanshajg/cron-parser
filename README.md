# Cron parser
This is a simple cron parser that takes a cron string and returns a human-readable format.

## Pre-requisites
- Java 21 or higher

## How to run
1. build the jar file
2. run the jar file with the cron string as an argument

The above steps are automated using makefile
```bash
make
```
this would clean the project, run tests, build the jar file and run the jar file with a default cron string.
please update the cron string in the makefile if you want to test with a different cron string.

for subsequent runs, you can run the jar file with the cron string as an argument
```bash
make run CRON="*/15 0 1,15 * 1-5 /usr/bin/echo hello world"
```

## Directory structure
```
com.vanshajgirotra.cron.parser/
├── parsers/* -> contains all the parser implementation
├── CronParser -> main class that parses the cron string
```

