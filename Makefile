# Define variables
MVN = ./mvnw
TARGET_DIR = target
JAR_FILE = $(TARGET_DIR)/cron-parser-1.0-SNAPSHOT.jar
MAIN_CLASS = com.vanshajgirotra.cron.parser.CronParser

# Default cron expression
DEFAULT_CRON = "*/15 0 1,15 * 1-5 /usr/bin/find"

# The cron expression to use, defaulting to DEFAULT_CRON if not set
CRON ?= $(DEFAULT_CRON)


# The default goal
.PHONY: all
all: clean package run

# Clean the project
.PHONY: clean
clean:
	$(MVN) clean

# Package the project into a JAR
.PHONY: package
package:
	$(MVN) package

# Run the packaged JAR file with the cron expression
.PHONY: run
run:
	@echo "Running with CRON: $(CRON)"
	java -cp $(JAR_FILE) $(MAIN_CLASS) '$(subst ','\'',$(CRON))'