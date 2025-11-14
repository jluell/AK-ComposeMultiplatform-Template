package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a player's ranking in the yearly summary.
 *
 * @property playerId ID of the player
 * @property bestNScores List of the player's best N scores
 * @property bestNTotal Sum of the best N scores
 * @property rank Final rank (1 = highest, ties share the same rank)
 */
@Serializable
data class PlayerRanking(
    val playerId: String,
    val bestNScores: List<Int>,
    val bestNTotal: Int,
    val rank: Int
) {
    init {
        require(rank > 0) { "Rank must be positive" }
        require(bestNTotal == bestNScores.sum()) {
            "Best N total must equal sum of best N scores"
        }
    }
}

