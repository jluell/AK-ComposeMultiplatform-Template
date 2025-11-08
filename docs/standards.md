# Coding Standards

This document defines coding conventions, style guidelines, and best practices for the project.

## Kotlin Style Guide

The project follows the [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html) with project-specific additions.

### Naming Conventions

#### Packages

- **Lowercase** with no underscores
- **Client code**: `de.appkreativ.cmp.*`
- **Shared modules**: `shared.{module}.*`
  - `shared.core.*`
  - `shared.domain.*`
  - `shared.data.*`
  - `shared.presentation.*`

#### Classes and Objects

- **PascalCase** (UpperCamelCase)
- **Classes**: Nouns (e.g., `UserRepository`, `HomeViewModel`)
- **Interfaces**: Nouns or adjectives (e.g., `Repository`, `ViewState`)
- **Sealed classes**: Nouns (e.g., `UiState`, `Result`)

#### Functions and Variables

- **camelCase**
- **Functions**: Verbs (e.g., `getUser()`, `loadData()`)
- **Variables**: Nouns (e.g., `userName`, `isLoading`)
- **Boolean**: Prefix with `is`, `has`, `can`, `should` (e.g., `isLoading`, `hasError`)

#### Constants

- **UPPER_SNAKE_CASE**
- Defined in `object` or top-level
- Example: `const val MAX_RETRY_COUNT = 3`

#### Design System Components

- **Prefix with `Ds`**: `DsButton`, `DsText`, `DsCard`
- Indicates reusable UI components

### File Organization

#### File Naming

- **One class per file** (except for small data classes)
- **File name matches class name**: `HomeViewModel.kt` contains `HomeViewModel`
- **Kotlin files**: `.kt` extension

#### File Structure

```kotlin
// 1. Package declaration
package de.appkreativ.cmp.home.presentation

// 2. Imports (grouped and sorted)
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// 3. Class/object/function
@Composable
fun HomeScreen() {
    // Implementation
}
```

#### Import Organization

1. **Kotlin standard library**
2. **Kotlinx libraries** (coroutines, serialization)
3. **Android/Compose libraries**
4. **Third-party libraries**
5. **Project imports** (shared modules, client modules)

Use IDE auto-formatting for consistent import ordering.

### Code Formatting

#### Indentation

- **4 spaces** (no tabs)
- Configure IDE to use spaces

#### Line Length

- **Maximum 120 characters** per line
- Break long lines for readability

#### Braces

- **Opening brace on same line** for classes, functions, if/else
- **Opening brace on new line** for multi-line expressions (optional)

```kotlin
// Preferred
class MyClass {
    fun myFunction() {
        if (condition) {
            // code
        }
    }
}
```

#### Blank Lines

- **One blank line** between top-level declarations
- **No blank lines** between related declarations (e.g., properties in a class)
- **Blank lines** to separate logical sections

### Class Structure

#### Property Order

1. **Companion objects**
2. **Properties** (public → private)
3. **Initialization blocks**
4. **Functions** (public → private)
5. **Nested classes**

#### Function Order

1. **Public functions**
2. **Protected functions**
3. **Private functions**
4. **Companion object functions**

### Documentation

#### KDoc Comments

Use KDoc for public APIs:

```kotlin
/**
 * Retrieves a user by ID.
 *
 * @param id The unique identifier of the user
 * @return Result containing the user or an error
 * @throws IllegalArgumentException if id is empty
 */
suspend fun getUser(id: String): Result<User>
```

#### Inline Comments

- Use sparingly
- Explain **why**, not **what**
- Keep comments up to date

### Type Safety

#### Null Safety

- **Prefer non-nullable types** when possible
- **Use nullable types** only when necessary
- **Use safe calls** (`?.`) and elvis operator (`?:`)

```kotlin
// Preferred
val name: String = user.name ?: "Unknown"

// Avoid
val name: String? = user.name
```

#### Type Inference

- **Use type inference** when type is obvious
- **Explicit types** for public APIs and complex expressions

```kotlin
// Preferred
val user = getUser()

// Explicit when needed
val user: User = getUser()
```

### Functions

#### Function Parameters

- **Maximum 5 parameters** (consider data class for more)
- **Default parameters** for optional arguments
- **Named arguments** for clarity when calling

```kotlin
fun createUser(
    firstName: String,
    lastName: String,
    email: String? = null,
    age: Int = 0
) { /* ... */ }

// Call with named arguments
createUser(
    firstName = "John",
    lastName = "Doe",
    email = "john@example.com"
)
```

#### Function Scope

- **Prefer `private`** for internal functions
- **Use `internal`** for module-internal APIs
- **Use `public`** (default) for public APIs

### Data Classes

#### When to Use

- **Immutable data containers**
- **Value objects**
- **DTOs** (Data Transfer Objects)

#### Best Practices

- **Use `val`** for properties (immutability)
- **Use `data class`** for equality comparison
- **Avoid mutable properties** in data classes

```kotlin
data class User(
    val id: String,
    val name: String,
    val email: String
)
```

### Sealed Classes and Interfaces

Use sealed classes/interfaces for:

- **State representation** (`UiState`, `ViewState`)
- **Result types** (`Result<T>`)
- **Limited hierarchies**

```kotlin
sealed interface UiState {
    object Ready : UiState
    object Loading : UiState
    data class Error(val message: String) : UiState
}
```

### Coroutines

#### ViewModel Coroutines

Use ViewModel's coroutine helpers:

```kotlin
// UI thread
ui("operationId") {
    // Update UI state
}

// Background thread
async("operationId") {
    // Background work
}
```

#### Error Handling

Always handle errors in coroutines:

```kotlin
async("loadData") {
    try {
        val data = repository.getData()
        withState {
            state.data = data
        }
    } catch (e: Exception) {
        state.uiState = e.toErrorState("Error") {
            state.uiState = UiState.Ready
        }
    }
}
```

### Compose Guidelines

#### Composable Functions

- **PascalCase** for composable functions
- **@Composable** annotation
- **Modifier parameter** as first optional parameter

```kotlin
@Composable
fun MyComponent(
    modifier: Modifier = Modifier,
    text: String
) {
    // Implementation
}
```

#### State Management

- **Use ViewModel** for screen-level state
- **Use `remember`** for local UI state
- **Use `mutableStateOf`** for simple state

#### Recomposition

- **Minimize recomposition** by using `remember`
- **Use `@Stable`** for state interfaces
- **Avoid creating objects** in composable body

### Testing

#### Test Naming

- **Descriptive test names**: `testGetUser_WhenIdIsValid_ReturnsUser()`
- **Use backticks** for readable test names: `` `should return user when id is valid` ``

#### Test Structure

```kotlin
@Test
fun `should return user when id is valid`() {
    // Given
    val repository = UserRepositoryImpl(mockDataSource)
    
    // When
    val result = repository.getUser("123")
    
    // Then
    assertTrue(result.isSuccess)
}
```

## Module-Specific Guidelines

### Domain Layer

- **No external dependencies** (except `shared/core`)
- **Pure Kotlin** code
- **Immutable** data classes
- **Interfaces** for repository contracts

### Data Layer

- **Implement domain interfaces**
- **Platform-specific** implementations when needed
- **Error handling** with Result pattern
- **Repository pattern** implementation

### Presentation Layer

- **Compose UI** components
- **ViewModels** extend `BaseViewModel`
- **State management** with `UiState`/`ViewState`
- **Design system** components (`Ds*`)

### Client Module

- **Feature-based** organization
- **DI modules** per feature
- **Platform entry points**
- **Navigation** setup

## Code Review Checklist

- [ ] Follows naming conventions
- [ ] Proper null safety handling
- [ ] Error handling implemented
- [ ] KDoc comments for public APIs
- [ ] No hardcoded strings (use resources)
- [ ] Proper use of coroutines
- [ ] State management follows patterns
- [ ] Tests written (when applicable)
- [ ] No platform-specific code in common source sets (unless expected/actual)

## IDE Configuration

### IntelliJ IDEA / Android Studio

1. **Code Style**: Use Kotlin style guide
2. **Inspections**: Enable Kotlin inspections
3. **Auto-format**: Format on save
4. **Import optimization**: Optimize imports on save

### Recommended Settings

- **Hard wrap**: 120 characters
- **Indent**: 4 spaces
- **Continuation indent**: 4 spaces
- **Tab size**: 4 spaces

## Resources

- [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Android Kotlin Style Guide](https://developer.android.com/kotlin/style-guide)
- [Compose Guidelines](https://developer.android.com/jetpack/compose/guidelines)

## Next Steps

- See [Architecture Documentation](architecture.md) for architectural patterns
- Review [Structure Documentation](structure.md) for module organization
- Check [Decision Log](decision-log.md) for architectural decisions

