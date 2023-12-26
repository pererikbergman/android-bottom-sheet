# Readme for EditableTextLabel Kotlin Repository

## Introduction

This repository contains Kotlin code for an editable text label feature implemented using Jetpack Compose in an Android application. The primary functionality is to display text data that can be edited through a modal bottom sheet.

## Features

- **Display of Current Data:** The main screen displays the current text data.
- **Editable Capability:** Users can edit the text by invoking a modal bottom sheet. The text field in the bottom sheet is automatically focused, prompting the keyboard to appear for immediate editing.
- **Confirmation Dialog:** A confirmation dialog appears upon successful update of the data.

## Requirements

- Android Studio
- Kotlin
- Jetpack Compose (Material3)

## Installation

To use this code in your project, follow these steps:

1. Clone this repository.
2. Import the project into Android Studio.
3. Ensure that you have the latest version of Kotlin and Jetpack Compose configured.
4. Build and run the application.

## Usage

The `EditableTextLabelScreen` composable function is the main entry point. It displays the current data and a button to edit the data.

When the "Edit Data" button is clicked:

1. A modal bottom sheet (`EditBottomSheetContent`) is presented allowing the user to edit the text.
2. After editing, clicking the "Save" button updates the data and closes the bottom sheet.
3. A confirmation dialog is shown to indicate that the data has been updated.

## Code Structure

- **EditableTextLabelScreen:** This composable displays the main screen with current data and the edit button.
- **EditBottomSheetContent:** A composable that renders the bottom sheet content for editing the text.

## Contributions

Contributions are welcome. Please fork the repository and submit a pull request with your changes.

## License

This project is open source and available under the [MIT License](LICENSE.md).