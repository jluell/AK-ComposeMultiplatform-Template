package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a single round score in the 40-shot game.
 *
 * @property roundNumber Round number (1-4)
 * @property lane Lane side (LEFT or RIGHT)
 * @property throws List of 10 throws, each value 0-9
 * @property total Sum of all throws in this round
 */
@Serializable
data class RoundScore(
    val roundNumber: Int,
    val lane: Lane,
    val throws: List<Int>,
    val total: Int
) {
    init {
        require(roundNumber in 1..4) { "Round number must be between 1 and 4" }
        require(throws.size == 10) { "Must have exactly 10 throws" }
        require(throws.all { it in 0..9 }) { "All throws must be between 0 and 9" }
        require(total == throws.sum()) { "Total must equal sum of throws" }
    }
}

