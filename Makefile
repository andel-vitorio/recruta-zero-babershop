GRADLEW = ./gradlew

MAIN_CLASS = com.example.MainClass

run: build
ifeq ($(ARGS),)
	@echo "Running the project without arguments..."
	$(GRADLEW) run
else
	@echo "Running the project with arguments: $(ARGS)"
	$(GRADLEW) run --args "$(ARGS)"
endif

build:
	@echo "Building the project..."
	$(GRADLEW) build

clean:
	@echo "Cleaning the project..."
	$(GRADLEW) clean

.PHONY: run build clean
