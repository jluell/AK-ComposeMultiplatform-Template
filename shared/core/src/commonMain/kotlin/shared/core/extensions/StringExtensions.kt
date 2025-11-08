package shared.core.extensions

/**
 * Common extension functions.
 * 
 * This package contains shared extension functions that can be used across all modules.
 */

/**
 * Returns true if the string is not null and not blank.
 */
fun String?.isNotNullOrBlank(): Boolean = !isNullOrBlank()

/**
 * Returns true if the string is null or blank.
 */
fun String?.isNullOrBlank(): Boolean = this == null || isBlank()

/**
 * Returns the string if not null or blank, otherwise returns the default value.
 */
fun String?.orDefault(default: String): String = if (isNotNullOrBlank()) this!! else default

/**
 * Executes the block if the receiver is not null.
 */
inline fun <T> T?.ifNotNull(block: (T) -> Unit) {
    if (this != null) block(this)
}

/**
 * Executes the block if the receiver is null.
 */
inline fun <T> T?.ifNull(block: () -> Unit) {
    if (this == null) block()
}

