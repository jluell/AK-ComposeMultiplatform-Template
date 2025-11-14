package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents the status of a bowling event.
 */
@Serializable
enum class EventStatus {
    PLANNED,
    IN_PROGRESS,
    COMPLETED
}

