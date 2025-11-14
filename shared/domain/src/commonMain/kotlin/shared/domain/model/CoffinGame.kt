package shared.domain.model

import kotlin.time.Instant
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a coffin game for an event.
 *
 * @property id Unique identifier for the coffin game
 * @property eventId ID of the event this game belongs to
 * @property playerOrder List of player IDs in randomized order
 * @property turns List of all turns played in the game
 * @property eliminations List of player eliminations
 * @property createdAt Timestamp when the game was created
 */

@OptIn(ExperimentalTime::class)
@Serializable
data class CoffinGame constructor(
    val id: String,
    val eventId: String,
    val playerOrder: List<String>,
    val turns: List<CoffinTurn>,
    val eliminations: List<CoffinElimination>,
    val createdAt: Instant
) {
    init {
        require(playerOrder.isNotEmpty()) { "Player order must not be empty" }
        require(playerOrder.size == playerOrder.distinct().size) {
            "Player order must not contain duplicates"
        }
    }
}

