# Project Overview

For additional context and project details, please consult the `README.md` and `devnotes.md` files.

This project appears to be an Android application named "Password Gen", developed using Kotlin and Jetpack Compose. It is structured as a Gradle project.

There is a discrepancy between the `README.md` file, which describes the project as "android-pwdgen" and a "Minimalistic Task and Todo app", and the `settings.gradle.kts` file, which names the project "Password Gen". The application ID `alphainterplanetary.passwordgen` in `app/build.gradle.kts` further supports the "Password Gen" identity. It is likely that the `README.md` is outdated or refers to a previous iteration of the project.

The project uses:
*   **Language:** Kotlin
*   **UI Toolkit:** Jetpack Compose
*   **Build System:** Gradle
*   **Target Platform:** Android (minSdk 26, targetSdk 35, compileSdk 34)

**Note:** The primary source code files (e.g., `MainActivity.kt`) could not be located within the expected `app/src/main/java/alphainterplanetary/passwordgen/` directory. This suggests the project might be incomplete, or the source code is located in a non-standard path not discoverable through standard means.

## Building and Running

To build and run this Android project, you would typically use the Gradle wrapper.

**Build the project:**

```bash
./gradlew build
```

**Install and run on a connected device or emulator:**

```bash
./gradlew installDebug
./gradlew runDebug # (This command might vary depending on the project setup, often done via Android Studio)
```

**Run tests:**

```bash
./gradlew test
./gradlew connectedCheck # For instrumentation tests
```

**Note:** Due to the absence of discoverable source code, the actual functionality and successful execution of these commands cannot be fully guaranteed without further investigation or code provision.

## Development Conventions

Based on the `build.gradle.kts` files, the project adheres to modern Android development practices using Kotlin and Jetpack Compose. Specific coding styles, testing practices, or contribution guidelines cannot be fully inferred without access to the project's source code. However, it is expected to follow standard Kotlin and Compose best practices.
