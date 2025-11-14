package shared.domain.model

import kotlin.time.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a monthly bowling event.
 *
 * @property id Unique identifier for the event
 * @property date Date of the event (should be 4th Thursday of month)
 * @property location Location of the event (default: "Hotel zur Börse")
 * @property status Current status of the event
 * @property createdAt Timestamp when the event was created
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class Event(
    val id: String,
    val date: LocalDate,
    val location: String = "Hotel zur Börse",
    val status: EventStatus = EventStatus.PLANNED,
    val createdAt: Instant
)

