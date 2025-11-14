package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a player's elimination from the coffin game.
 *
 * @property playerId ID of the eliminated player
 * @property eliminationTurn Turn number when player was eliminated
 * @property finalRank Final ranking in the coffin game (1 = winner, higher = eliminated earlier)
 */
@Serializable
data class CoffinElimination(
    val playerId: String,
    val eliminationTurn: Int,
    val finalRank: Int
) {
    init {
        require(eliminationTurn > 0) { "Elimination turn must be positive" }
        require(finalRank > 0) { "Final rank must be positive" }
    }
}

