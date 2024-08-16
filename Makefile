# Define variables
MVN = ./mvnw
CLEAN_CMD = $(MVN) clean
PACKAGE_CMD = $(MVN) package
JAR_NAME = target/my-app-1.0-SNAPSHOT.jar

# Default target
all: clean build

# Rule to build the project
build:
	@echo "Building the project..."
	$(PACKAGE_CMD)

# Rule to clean build artifacts
clean:
	@echo "Cleaning up..."
	$(CLEAN_CMD)

# Rule to run the JAR file
run: $(JAR_NAME)
	@echo "Running the JAR file..."
	java -jar $(JAR_NAME)

# Phony targets
.PHONY: all clean build run
