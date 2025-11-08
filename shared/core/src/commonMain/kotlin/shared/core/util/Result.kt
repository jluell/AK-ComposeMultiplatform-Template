package shared.core.util

/**
 * Common utility functions and helpers.
 * 
 * This package contains shared utility functions that can be used across all modules
 * without introducing external dependencies.
 */

/**
 * Result wrapper for operations that can fail.
 * 
 * @param T The type of the success value
 */
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Throwable) : Result<Nothing>()
    
    val isSuccess: Boolean get() = this is Success
    val isError: Boolean get() = this is Error
    
    inline fun <R> fold(
        onSuccess: (T) -> R,
        onError: (Throwable) -> R
    ): R = when (this) {
        is Success -> onSuccess(data)
        is Error -> onError(exception)
    }
}

/**
 * Executes a block and returns a Result.
 */
inline fun <T> runCatchingResult(block: () -> T): Result<T> = try {
    Result.Success(block())
} catch (e: Throwable) {
    Result.Error(e)
}

/**
 * Executes a suspend block and returns a Result.
 */
suspend inline fun <T> runCatchingResultSuspend(block: suspend () -> T): Result<T> = try {
    Result.Success(block())
} catch (e: Throwable) {
    Result.Error(e)
}

