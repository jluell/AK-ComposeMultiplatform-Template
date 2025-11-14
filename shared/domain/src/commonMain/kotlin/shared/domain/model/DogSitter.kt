package shared.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

/**
 * Represents the dog sitter assignment for an event.
 * The dog sitter is the player with the most penalties who takes the penalty object home.
 *
 * @property id Unique identifier for the dog sitter record
 * @property eventId ID of the event
 * @property playerId ID of the player assigned as dog sitter
 * @property assignedDate Date when the assignment was made
 * @property isCurrent Whether this is the current dog sitter (has the penalty object)
 */
@Serializable
data class DogSitter(
    val id: String,
    val eventId: String,
    val playerId: String,
    val assignedDate: LocalDate,
    val isCurrent: Boolean
)

