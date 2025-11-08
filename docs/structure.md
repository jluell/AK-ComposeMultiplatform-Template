# Project Structure

This document describes the directory structure, module organization, and source set hierarchy of the project.

## Directory Structure

```
AK-ComposeMultiplatform-Template/
├── client/                    # Main application module
│   ├── src/
│   │   ├── androidMain/       # Android-specific code
│   │   ├── commonMain/        # Shared code for all platforms
│   │   ├── iosMain/           # iOS-specific code
│   │   ├── jsMain/            # Web-specific code
│   │   └── jvmMain/           # Desktop-specific code
│   ├── iosApp/                # iOS Xcode project
│   └── build.gradle.kts
├── server/                     # Ktor backend server
│   ├── src/main/
│   └── build.gradle.kts
├── shared/                     # Shared modules
│   ├── core/                  # Core utilities (no dependencies)
│   │   ├── src/commonMain/
│   │   └── build.gradle.kts
│   ├── data/                  # Data layer
│   │   ├── src/
│   │   │   ├── commonMain/    # Repository interfaces, network setup
│   │   │   ├── androidMain/   # Android HTTP client
│   │   │   ├── iosMain/       # iOS HTTP client
│   │   │   ├── jsMain/        # JS HTTP client
│   │   │   └── jvmMain/       # JVM HTTP client
│   │   └── build.gradle.kts
│   ├── domain/                # Domain layer (business logic)
│   │   ├── src/commonMain/
│   │   └── build.gradle.kts
│   └── presentation/          # Presentation layer (UI)
│       ├── src/
│       │   ├── commonMain/    # Compose UI, ViewModels, theming
│       │   ├── androidMain/   # Android-specific UI
│       │   ├── skikoMain/     # Skiko-specific code
│       │   └── jvmMain/       # Desktop-specific UI
│       └── build.gradle.kts
├── docs/                      # Documentation
│   ├── Platforms/             # Platform-specific guides
│   └── [core documentation files]
├── gradle/                     # Gradle configuration
│   ├── libs.versions.toml     # Dependency versions
│   └── wrapper/               # Gradle wrapper
├── build.gradle.kts           # Root build file
├── settings.gradle.kts        # Project settings
└── README.md
```

## Module Organization

### Clean Architecture Layers

The project follows Clean Architecture with the following module structure:

#### 1. Core Module (`shared/core`)

**Purpose**: Shared utilities and extensions with no external dependencies.

**Responsibilities**:
- Utility functions
- Extension functions
- Common helpers
- Result wrappers

**Dependencies**: None

**Package Structure**:
```
shared.core/
├── util/          # Utility functions (Result, etc.)
└── extensions/    # Extension functions
```

#### 2. Domain Module (`shared/domain`)

**Purpose**: Business logic and domain models. Pure Kotlin with no platform dependencies.

**Responsibilities**:
- Domain models
- Business logic
- Use cases (future)
- Domain interfaces

**Dependencies**: 
- `shared/core` (utilities)

**Package Structure**:
```
shared.domain/
└── model/         # Domain models
```

#### 3. Data Module (`shared/data`)

**Purpose**: Data layer implementation with networking and repositories.

**Responsibilities**:
- Repository implementations
- Remote data sources (Ktor client)
- Local data sources (future: database, cache)
- Network configuration

**Dependencies**:
- `shared/core` (utilities)
- `shared/domain` (domain models and interfaces)
- Ktor Client (networking)
- Kotlinx Serialization

**Package Structure**:
```
shared.data/
├── network/       # HTTP client setup
└── repository/    # Repository interfaces
```

**Platform-Specific Implementations**:
- `androidMain`: Android HTTP engine
- `iosMain`: Darwin HTTP engine
- `jsMain`: JavaScript HTTP engine
- `jvmMain`: Java HTTP engine

#### 4. Presentation Module (`shared/presentation`)

**Purpose**: UI layer with Compose Multiplatform components and ViewModels.

**Responsibilities**:
- Compose UI components
- ViewModels (MVVM pattern)
- Navigation setup
- Theming system
- Design system components

**Dependencies**:
- `shared/core` (utilities)
- `shared/data` (repositories)
- `shared/domain` (domain models)
- Compose Multiplatform
- Koin (DI)
- Navigation Compose

**Package Structure**:
```
shared.presentation/
├── theme/         # Theming system
├── viewmodel/     # Base ViewModel
├── state/         # State management
├── navigation/    # Navigation utilities
└── ui/            # UI components
    ├── component/ # Reusable components
    ├── container/ # Container components
    └── theme/     # Theme definitions
```

#### 5. Client Module (`client`)

**Purpose**: Platform-specific entry points and app-level configuration.

**Responsibilities**:
- Platform entry points (main functions)
- App-level DI configuration
- Feature modules (app, home, common, platform)
- Navigation graph setup

**Dependencies**:
- All shared modules
- Platform-specific libraries

**Package Structure**:
```
client/
└── src/
    ├── commonMain/
    │   └── de.appkreativ.cmp/
    │       ├── app/           # App-level code
    │       ├── home/          # Home feature
    │       ├── common/        # Common features
    │       ├── platform/      # Platform abstraction
    │       └── DI.kt          # Dependency injection
    ├── androidMain/           # Android entry point
    ├── iosMain/              # iOS entry point
    ├── jsMain/               # Web entry point
    └── jvmMain/              # Desktop entry point
```

#### 6. Server Module (`server`)

**Purpose**: Ktor backend server.

**Responsibilities**:
- API endpoints
- Server configuration
- SPA hosting (for web app)

**Dependencies**:
- `shared/domain` (shared models)
- Ktor Server

## Source Set Hierarchy

Kotlin Multiplatform uses source sets to organize platform-specific and shared code:

### Common Source Sets

- **commonMain**: Code shared across all platforms
- **commonTest**: Tests shared across all platforms

### Platform-Specific Source Sets

- **androidMain**: Android-specific code
- **iosMain**: iOS-specific code (shared by all iOS targets)
- **jsMain**: JavaScript/Web-specific code
- **jvmMain**: JVM/Desktop-specific code

### iOS Target-Specific

- **iosArm64**: iOS devices (ARM64)
- **iosX64**: iOS simulator (x64)
- **iosSimulatorArm64**: iOS simulator (ARM64)

### Hierarchical Source Sets

The project uses `applyDefaultHierarchyTemplate()` which creates:
- **nativeMain**: Shared by iOS targets
- **appleMain**: Shared by all Apple platforms

## Dependency Flow

```
client
  ├── shared/presentation
  │     ├── shared/data
  │     │     ├── shared/domain
  │     │     └── shared/core
  │     ├── shared/domain
  │     └── shared/core
  ├── shared/data
  ├── shared/domain
  └── shared/core

server
  └── shared/domain
```

**Key Principles**:
- Domain has no dependencies (except core utilities)
- Data depends on Domain (implements domain interfaces)
- Presentation depends on Domain and Data
- Client depends on all shared modules
- Core has no dependencies

## Package Naming Conventions

- **Client code**: `de.appkreativ.cmp.*`
- **Shared modules**: `shared.{module}.*`
  - `shared.core.*`
  - `shared.domain.*`
  - `shared.data.*`
  - `shared.presentation.*`

## File Organization Patterns

### Feature-Based Organization (Client)

Features are organized by domain:
```
client/src/commonMain/kotlin/de/appkreativ/cmp/
├── app/              # App-level feature
│   ├── AppConfig.kt  # DI module
│   └── presentation/ # UI and ViewModels
├── home/             # Home feature
│   ├── HomeConfig.kt
│   └── presentation/
└── common/           # Common/shared features
```

### Layer-Based Organization (Shared Modules)

Shared modules organize by architectural layer:
```
shared/{module}/src/commonMain/kotlin/shared/{module}/
├── network/          # Networking (data)
├── repository/       # Repositories (data)
├── model/           # Domain models (domain)
├── theme/           # Theming (presentation)
└── viewmodel/       # ViewModels (presentation)
```

## Build Configuration

### Version Catalog

Dependency versions are centralized in `gradle/libs.versions.toml`:
- `[versions]`: Version numbers
- `[libraries]`: Library declarations
- `[plugins]`: Plugin declarations

### Module Build Files

Each module has its own `build.gradle.kts`:
- Defines source sets
- Configures platform targets
- Declares dependencies
- Sets up Android-specific configuration (if applicable)

## Platform-Specific Considerations

### Android

- `AndroidManifest.xml` in `androidMain`
- Resources in `androidMain/res`
- Android-specific dependencies

### iOS

- Xcode project in `client/iosApp/`
- SwiftUI wrapper for Kotlin framework
- Framework linking configuration

### Desktop (JVM)

- Main function in `jvmMain/kotlin/main.kt`
- Compose Desktop configuration
- Native distribution settings

### Web (JS)

- HTML entry point in `jsMain/resources/index.html`
- Webpack configuration
- WASM support

## Next Steps

- See [Architecture Documentation](architecture.md) for architectural patterns
- See [Standards Documentation](standards.md) for coding conventions
- Review platform-specific guides in `docs/Platforms/`

