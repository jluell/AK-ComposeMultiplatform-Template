package shared.domain.model

import kotlin.time.Instant
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a player's 40-shot score for an event.
 *
 * @property id Unique identifier for the score record
 * @property eventId ID of the event this score belongs to
 * @property playerId ID of the player
 * @property rounds List of 4 round scores
 * @property totalScore Sum of all 40 throws
 * @property ninesHit Count of 9-pin hits in all rounds
 * @property createdAt Timestamp when the score was recorded
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class FortyShotScore(
    val id: String,
    val eventId: String,
    val playerId: String,
    val rounds: List<RoundScore>,
    val totalScore: Int,
    val ninesHit: Int,
    val createdAt: Instant
) {
    init {
        require(rounds.size == 4) { "Must have exactly 4 rounds" }
        require(rounds.map { it.lane }.count { it == Lane.LEFT } == 2) {
            "Must have exactly 2 rounds on LEFT lane"
        }
        require(rounds.map { it.lane }.count { it == Lane.RIGHT } == 2) {
            "Must have exactly 2 rounds on RIGHT lane"
        }
        require(totalScore == rounds.sumOf { it.total }) {
            "Total score must equal sum of all round totals"
        }
        require(ninesHit == rounds.sumOf { round -> round.throws.count { it == 9 } }) {
            "Nines hit count must match actual count in throws"
        }
    }
}

