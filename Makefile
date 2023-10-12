# Makefile for Java project using Gradle Wrapper

# Set the Gradle Wrapper command (use ./gradlew for Unix systems and gradlew.bat for Windows)
GRADLEW = ./gradlew

# Default arguments (empty)
ARGS =

# Default target: build and run the project
run: build
ifneq ($(ARGS),)
	@echo "Running the project with arguments: $(ARGS)"
	$(GRADLEW) run --args="$(ARGS)"
else
	@echo "Running the project without arguments..."
	$(GRADLEW) run
endif

# Build the project
build:
	@echo "Building the project..."
	$(GRADLEW) build

# Clean the build (remove generated artifacts)
clean:
	@echo "Cleaning the project..."
	$(GRADLEW) clean

.PHONY: run build clean
