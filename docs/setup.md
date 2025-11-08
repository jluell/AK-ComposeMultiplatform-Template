# Setup Guide

This guide will help you set up your development environment and build the project for all supported platforms.

## Prerequisites

### Required Software

1. **JDK 11 or higher**
   - Download from [Adoptium](https://adoptium.net/) or [Oracle](https://www.oracle.com/java/technologies/downloads/)
   - Verify installation: `java -version`

2. **Android Studio Hedgehog (2023.1.1) or later**
   - Download from [developer.android.com](https://developer.android.com/studio)
   - Includes Android SDK, Gradle, and Kotlin plugin

3. **Xcode 14+ (for iOS development)**
   - macOS only
   - Download from Mac App Store
   - Required for iOS builds and simulator

4. **Node.js 18+ (for Web builds)**
   - Download from [nodejs.org](https://nodejs.org/)
   - Required for JavaScript/WebAssembly builds

### Optional Tools

- **IntelliJ IDEA** or **Android Studio** (recommended IDE)
- **Git** for version control

## Environment Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd AK-ComposeMultiplatform-Template
```

### 2. Configure Android SDK

Ensure Android SDK is installed via Android Studio:
- Open Android Studio
- Go to Settings → Appearance & Behavior → System Settings → Android SDK
- Install SDK Platform 36 (or the version specified in `gradle/libs.versions.toml`)

### 3. Configure Local Properties

Create or update `local.properties` in the project root:

```properties
sdk.dir=/path/to/your/android/sdk
```

On macOS/Linux:
```properties
sdk.dir=/Users/yourusername/Library/Android/sdk
```

On Windows:
```properties
sdk.dir=C\:\\Users\\yourusername\\AppData\\Local\\Android\\Sdk
```

### 4. iOS Setup (macOS only)

1. Install Xcode from Mac App Store
2. Open Xcode and accept license agreements
3. Install Xcode Command Line Tools:
   ```bash
   xcode-select --install
   ```
4. Install CocoaPods (if not already installed):
   ```bash
   sudo gem install cocoapods
   ```

## Building the Project

### Build All Modules

```bash
./gradlew build
```

### Build Specific Modules

```bash
# Build client module
./gradlew :client:build

# Build shared modules
./gradlew :shared:domain:build
./gradlew :shared:presentation:build
./gradlew :shared:data:build
./gradlew :shared:core:build

# Build server
./gradlew :server:build
```

## Running the Application

### Android

#### Using Gradle

```bash
# Debug build
./gradlew :client:installDebug

# Run on connected device/emulator
./gradlew :client:installDebug
adb shell am start -n de.appkreativ.cmp.debug/de.appkreativ.cmp.AppActivity
```

#### Using Android Studio

1. Open the project in Android Studio
2. Select an Android device or emulator
3. Click Run (▶️) or press `Shift+F10`

### iOS

#### Prerequisites

- macOS with Xcode installed
- iOS Simulator or physical device

#### Build and Run

```bash
# Build iOS framework
./gradlew :client:linkDebugFrameworkIosArm64
# or for simulator
./gradlew :client:linkDebugFrameworkIosSimulatorArm64

# Open Xcode project
open client/iosApp/iosApp.xcodeproj

# In Xcode:
# 1. Select target device/simulator
# 2. Click Run (▶️)
```

**Note**: The iOS app uses a SwiftUI wrapper (`ContentView.swift`) that integrates the Kotlin framework.

### Desktop (JVM)

#### Using Gradle

```bash
# Run desktop application
./gradlew :client:run

# Run with hot reload (development)
./gradlew :client:runHot
```

#### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Navigate to `client/src/jvmMain/kotlin/main.kt`
3. Right-click → Run 'main'

### Web (JavaScript)

#### Prerequisites

- Node.js 18+ installed
- Yarn (installed automatically via Gradle)

#### Build and Run

```bash
# Build for development
./gradlew :client:jsBrowserDevelopmentWebpack

# Build for production
./gradlew :client:jsBrowserProductionWebpack

# Serve locally (after build)
cd client/build/dist/js/developmentExecutable
python3 -m http.server 8000
# or
npx serve .

# Open in browser
# http://localhost:8000
```

#### Using Development Server

The project includes a Ktor server that can serve the web app:

```bash
# Build and run server with SPA
./gradlew :server:runDevSPA

# Access at http://localhost:8080
```

## Running the Backend Server

### Development Mode

```bash
# Run server
./gradlew :server:run

# Server runs on http://localhost:8080
```

### With Web App (SPA Mode)

```bash
# Build web app and run server
./gradlew :server:runDevSPA

# Access web app at http://localhost:8080
```

### Production Build

```bash
# Build production web app and server
./gradlew :server:assembleSPA

# Run production server
./gradlew :server:run
```

## Troubleshooting

### Build Issues

**Problem**: Gradle build fails with "SDK not found"
- **Solution**: Ensure `local.properties` contains correct `sdk.dir` path

**Problem**: iOS build fails
- **Solution**: Ensure Xcode and Command Line Tools are installed and up to date

**Problem**: Web build fails
- **Solution**: Ensure Node.js 18+ is installed and accessible in PATH

### Runtime Issues

**Problem**: Android app crashes on startup
- **Solution**: Check logcat: `adb logcat | grep -i error`

**Problem**: iOS app doesn't launch
- **Solution**: Check Xcode console for errors, ensure framework is properly linked

**Problem**: Desktop app window doesn't appear
- **Solution**: Check console output, ensure Java 11+ is being used

## IDE Configuration

### IntelliJ IDEA / Android Studio

1. **Import Project**
   - File → Open → Select project root directory
   - Choose "Import project from external model" → Gradle

2. **Kotlin Plugin**
   - Ensure Kotlin plugin is installed and up to date
   - Settings → Plugins → Kotlin

3. **Gradle Settings**
   - Settings → Build, Execution, Deployment → Build Tools → Gradle
   - Use Gradle from: 'gradle-wrapper.properties' file
   - Gradle JVM: Project SDK (11 or higher)

4. **Code Style**
   - The project follows Kotlin coding conventions
   - See [Standards Documentation](standards.md) for details

## Next Steps

- Read [Architecture Documentation](architecture.md) to understand the project structure
- Review [Structure Documentation](structure.md) for module organization
- Check [Standards Documentation](standards.md) for coding conventions
- See platform-specific guides in `docs/Platforms/`

