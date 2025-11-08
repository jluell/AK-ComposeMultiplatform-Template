# Project Overview

## Introduction

This is a Kotlin Multiplatform (KMP) project template built with Compose Multiplatform, following Clean Architecture principles and the MVVM pattern. The project provides a foundation for building cross-platform applications that run on Android, iOS, Desktop (JVM), and Web (JavaScript).

## Architecture

The project follows **Clean Architecture** with clear separation of concerns across multiple layers:

### Layer Structure

```
┌─────────────────────────────────────────┐
│         Presentation Layer              │
│    (shared/presentation)                │
│    - UI Components (Compose)            │
│    - ViewModels (MVVM)                  │
│    - Navigation                          │
│    - Theming                            │
└──────────────┬──────────────────────────┘
               │ depends on
┌──────────────▼──────────────────────────┐
│          Domain Layer                   │
│    (shared/domain)                      │
│    - Business Logic                     │
│    - Domain Models                      │
│    - Use Cases                          │
│    - (No dependencies)                  │
└──────────────┬──────────────────────────┘
               │ depends on
┌──────────────▼──────────────────────────┐
│           Data Layer                     │
│    (shared/data)                        │
│    - Repositories                       │
│    - Data Sources                       │
│    - Networking (Ktor Client)           │
└─────────────────────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│           Core Layer                     │
│    (shared/core)                        │
│    - Utilities                          │
│    - Extensions                         │
│    - Common Helpers                     │
│    - (No dependencies)                  │
└─────────────────────────────────────────┘
```

### Dependency Flow

The dependency flow follows Clean Architecture principles:

- **Presentation → Domain**: Presentation layer depends on domain models and use cases
- **Data → Domain**: Data layer implements domain interfaces and uses domain models
- **Core**: Independent utilities used by all layers
- **Domain**: No dependencies (pure business logic)

## Technology Stack

### Core Technologies

- **Kotlin Multiplatform**: 2.2.21
- **Compose Multiplatform**: 1.9.1
- **Kotlin Coroutines**: 1.10.2
- **Kotlinx Serialization**: 1.9.0

### Dependency Injection

- **Koin**: 4.1.1
  - Koin Compose ViewModel Navigation integration
  - Feature-based module organization

### Networking

- **Ktor Client**: 3.3.1
  - Multiplatform HTTP client
  - JSON serialization
  - Logging support

### UI Framework

- **Compose Multiplatform**: Shared UI code across platforms
- **Material 3**: Design system components
- **Navigation Compose**: Type-safe navigation

### Backend

- **Ktor Server**: 3.3.1
  - Netty engine
  - JSON serialization
  - CORS support
  - Single Page Application hosting

## Project Goals

1. **Code Sharing**: Maximize code reuse across platforms while maintaining platform-specific optimizations
2. **Clean Architecture**: Maintain clear separation of concerns and testability
3. **MVVM Pattern**: Implement consistent state management and UI logic separation
4. **Scalability**: Provide a structure that scales with project growth
5. **Developer Experience**: Enable efficient development with clear patterns and documentation

## Platform Support

- ✅ **Android**: API 24+ (Android 7.0+)
- ✅ **iOS**: iOS 13+ (arm64, x64, simulator)
- ✅ **Desktop**: Windows, macOS, Linux (JVM)
- ✅ **Web**: Modern browsers (JavaScript/WebAssembly)

## Module Structure

- **client**: Main application module with platform-specific entry points
- **server**: Ktor backend server
- **shared/core**: Shared utilities and extensions
- **shared/data**: Data layer (repositories, networking)
- **shared/domain**: Domain layer (business logic, models)
- **shared/presentation**: Presentation layer (UI, ViewModels, theming)

## Getting Started

See [Setup Guide](setup.md) for environment setup and build instructions.

See [Architecture Documentation](architecture.md) for detailed architecture information.

See [Structure Documentation](structure.md) for module and directory structure details.

