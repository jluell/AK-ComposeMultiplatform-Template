# Architecture Documentation

This document describes the architectural patterns, design decisions, and implementation details of the project.

## Clean Architecture

The project follows **Clean Architecture** principles, organizing code into distinct layers with clear dependencies.

### Layer Responsibilities

#### 1. Domain Layer (`shared/domain`)

**Purpose**: Pure business logic with no external dependencies.

**Characteristics**:
- No dependencies on frameworks or libraries (except `shared/core` for utilities)
- Contains domain models, business rules, and use cases
- Platform-agnostic Kotlin code
- Testable without external dependencies

**Components**:
- **Domain Models**: Data classes representing business entities
- **Use Cases** (future): Business logic operations
- **Interfaces**: Contracts for data operations (repositories)

**Example**:
```kotlin
// shared/domain/src/commonMain/kotlin/shared/domain/model/User.kt
@Serializable
data class User(
    val firstName: String,
    val lastName: String
)
```

#### 2. Data Layer (`shared/data`)

**Purpose**: Data access and external communication.

**Characteristics**:
- Implements domain interfaces (repositories)
- Handles networking, caching, and persistence
- Platform-specific implementations where needed
- Depends on domain layer

**Components**:
- **Repositories**: Implement domain repository interfaces
- **Remote Data Sources**: Ktor client for API calls
- **Local Data Sources** (future): Database, cache implementations
- **Network Configuration**: HTTP client setup

**Example**:
```kotlin
// Repository interface in domain
interface UserRepository {
    suspend fun getUser(id: String): Result<User>
}

// Implementation in data layer
class UserRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override suspend fun getUser(id: String): Result<User> {
        return remoteDataSource.fetch(id)
    }
}
```

#### 3. Presentation Layer (`shared/presentation`)

**Purpose**: UI and user interaction logic.

**Characteristics**:
- Compose Multiplatform UI components
- ViewModels following MVVM pattern
- State management
- Depends on domain and data layers

**Components**:
- **UI Components**: Compose screens and reusable components
- **ViewModels**: Business logic for UI
- **State Management**: UiState, ViewState patterns
- **Navigation**: Navigation Compose setup
- **Theming**: Material 3 theme system

#### 4. Core Layer (`shared/core`)

**Purpose**: Shared utilities and extensions.

**Characteristics**:
- No dependencies (except Kotlin standard library)
- Reusable across all layers
- Platform-agnostic utilities

**Components**:
- **Result Wrapper**: Type-safe error handling
- **Extensions**: String, collection extensions
- **Utilities**: Common helper functions

### Dependency Rules

1. **Domain** has no dependencies (except core utilities)
2. **Data** depends on Domain (implements domain interfaces)
3. **Presentation** depends on Domain and Data
4. **Core** has no dependencies
5. Dependencies flow inward: Outer layers depend on inner layers

## MVVM Pattern

The project implements the **Model-View-ViewModel (MVVM)** pattern for UI architecture.

### Components

#### Model
- **Domain Models**: Defined in `shared/domain`
- **Data Sources**: Implemented in `shared/data`
- Represents business data and data access logic

#### View
- **Compose UI**: Defined in `shared/presentation/ui/`
- **Screens**: Feature-specific screens in client module
- Reactive UI that observes ViewModel state

#### ViewModel
- **BaseViewModel**: Base class in `shared/presentation/viewmodel/BaseViewModel.kt`
- **Feature ViewModels**: Extend BaseViewModel
- Manages UI state and business logic
- Lifecycle-aware coroutine management

### State Management

#### UiState

Represents the state of UI operations:

```kotlin
sealed interface UiState {
    object Ready : UiState
    object Loading : UiState
    object Blocking : UiState
    data class Error(
        val title: String,
        val th: Throwable,
        val onExit: () -> Unit
    ) : UiState
}
```

#### ViewState

Combines UI state with event flow:

```kotlin
interface ViewState {
    val uiState: UiState
    val uiEvent: Flow<UiEvent>
}
```

### ViewModel Lifecycle

`BaseViewModel` provides lifecycle hooks:

- **doInit()**: Called once when ViewModel is created
- **doBind()**: Called when ViewModel is bound to view
- **doResume()**: Called when view resumes
- **doPause()**: Called when view pauses
- **doDispose()**: Called when ViewModel is cleared

### Example ViewModel

```kotlin
class HomeViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {
    
    val state = HomeMutableState()
    
    override fun doInit() {
        loadUser()
    }
    
    private fun loadUser() {
        async("loadUser") {
            state.uiState = UiState.Loading
            userRepository.getUser("123").fold(
                onSuccess = { user ->
                    withState {
                        state.user = user
                        state.uiState = UiState.Ready
                    }
                },
                onError = { error ->
                    state.uiState = error.toErrorState("Failed to load user") {
                        state.uiState = UiState.Ready
                    }
                }
            )
        }
    }
}
```

### Unidirectional Data Flow

```
User Action → View → ViewModel → Repository → Data Source
                                      ↓
                              State Update
                                      ↓
                              View Recomposition
```

1. User interacts with View
2. View calls ViewModel method
3. ViewModel updates state or calls repository
4. Repository fetches/updates data
5. ViewModel updates state
6. View recomposes based on new state

## Dependency Injection (Koin)

The project uses **Koin** for dependency injection, organized by feature modules.

### Module Organization

Features define their own Koin modules:

```kotlin
// Feature module
val home = module {
    viewModelOf(::HomeViewModel)
    singleOf(::HomeMutableState).bind<HomeState>()
}

// App module includes feature modules
val app = module {
    includes(platform, common, home)
    viewModelOf(::AppViewModel)
    singleOf(::AppMutableState).bind<AppState>()
}
```

### ViewModel Injection

ViewModels are injected using Koin Compose integration:

```kotlin
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = getViewModel()
    val state = viewModel.state
    // Use state in UI
}
```

### Repository Injection

Repositories are provided in data layer modules:

```kotlin
val dataModule = module {
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { createHttpClient() }
}
```

## Navigation

Navigation uses **Navigation Compose** with type-safe route definitions.

### Route Organization

Routes are organized by feature:

```kotlin
fun NavGraphBuilder.app(navController: NavHostController) {
    platform(navController)
    common(navController)
    home(navController)
}
```

### Navigation Pattern

```kotlin
fun NavGraphBuilder.home(navController: NavHostController) {
    composable("home") {
        HomeScreen()
    }
}
```

## Networking (Ktor Client)

The data layer uses **Ktor Client** for HTTP communication.

### HTTP Client Setup

Platform-specific HTTP client factories:

```kotlin
// Common declaration
expect fun createHttpClient(
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient

// Platform implementations
// Android: Android engine
// iOS: Darwin engine
// JS: JavaScript engine
// JVM: Java engine
```

### Repository Pattern

Repositories use the HTTP client:

```kotlin
class UserRepositoryImpl(
    private val httpClient: HttpClient
) : UserRepository {
    override suspend fun getUser(id: String): Result<User> {
        return runCatchingResultSuspend {
            httpClient.get("${ApiConfig.baseUrl}/users/$id")
                .body<User>()
        }
    }
}
```

## Theming

The project uses **Material 3** theming with a custom design system.

### Theme Structure

- **ThemeProvider**: Composable that provides theme context
- **ThemeState**: Manages theme configuration (light/dark)
- **DsThemes**: Predefined theme configurations
- **DsTheme**: Material 3 theme setup

### Design System Components

Reusable components prefixed with `Ds`:
- `DsButton`, `DsText`, `DsCard`, `DsTextField`, etc.
- Consistent styling across the app
- Material 3 based

## Compose Multiplatform

### Shared UI Code

UI code is written once in `commonMain` and runs on all platforms:

```kotlin
@Composable
fun HomeScreen() {
    // Shared Compose code
    // Works on Android, iOS, Desktop, Web
}
```

### Platform-Specific UI

When needed, use `expect/actual` for platform-specific implementations:

```kotlin
// commonMain
expect fun PlatformSpecificComponent()

// androidMain
actual fun PlatformSpecificComponent() { /* Android impl */ }

// iosMain
actual fun PlatformSpecificComponent() { /* iOS impl */ }
```

## Error Handling

### Result Pattern

The project uses a `Result` wrapper for type-safe error handling:

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
}
```

### Error Handling in ViewModels

```kotlin
repository.getData().fold(
    onSuccess = { data ->
        // Handle success
    },
    onError = { error ->
        state.uiState = error.toErrorState("Error title") {
            state.uiState = UiState.Ready
        }
    }
)
```

## Testing Strategy

### Unit Tests

- **Domain**: Test business logic without dependencies
- **ViewModels**: Test with mocked repositories
- **Repositories**: Test with mocked data sources

### Integration Tests

- **Data Layer**: Test repository implementations
- **Navigation**: Test navigation flows

### UI Tests

- **Compose UI**: Test UI components
- **Screens**: Test screen behavior

## Best Practices

1. **Keep Domain Pure**: Domain layer should have no external dependencies
2. **Single Responsibility**: Each class/function has one responsibility
3. **Immutable State**: Use immutable data classes for state
4. **Type Safety**: Prefer sealed classes/interfaces for state
5. **Error Handling**: Always handle errors explicitly
6. **Lifecycle Awareness**: Use ViewModel lifecycle hooks appropriately
7. **Coroutine Management**: Use ViewModel's coroutine helpers (`ui`, `async`)
8. **State Updates**: Use `withState` for state mutations in ViewModels

## Next Steps

- See [Structure Documentation](structure.md) for module organization
- See [Standards Documentation](standards.md) for coding conventions
- Review [Decision Log](decision-log.md) for architectural decisions

