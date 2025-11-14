package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a bowling lane side.
 */
@Serializable
enum class Lane {
    LEFT,
    RIGHT
}

