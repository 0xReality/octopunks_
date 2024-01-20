#!/bin/bash

# Path to the JavaFX SDK lib directory
JAVAFX_SDK_PATH="lib"

# JavaFX modules used in the application
JAVAFX_MODULES="javafx.controls,javafx.fxml"

# Source files directory
SRC_DIR="UI/octoPunks.java"

# Output directory for compiled .class files
OUT_DIR="out"

# Main class with package
MAIN_CLASS="UI.octoPunks"

# Create output directory if it doesn't exist
mkdir -p ${OUT_DIR}

# Compile the Java files
javac --module-path ${JAVAFX_SDK_PATH} --add-modules ${JAVAFX_MODULES} -d ${OUT_DIR} ${SRC_DIR}

# Run the application
java --module-path ${JAVAFX_SDK_PATH} --add-modules ${JAVAFX_MODULES} -cp ${OUT_DIR} ${MAIN_CLASS}
