package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents a single turn in the coffin game.
 *
 * @property turnNumber Turn number in the game
 * @property playerId ID of the player taking this turn
 * @property score Score achieved in this turn (0-9)
 * @property boardsReceived Number of boards received in this turn (0 or 1)
 */
@Serializable
data class CoffinTurn(
    val turnNumber: Int,
    val playerId: String,
    val score: Int,
    val boardsReceived: Int
) {
    init {
        require(turnNumber > 0) { "Turn number must be positive" }
        require(score in 0..9) { "Score must be between 0 and 9" }
        require(boardsReceived in 0..1) { "Boards received must be 0 or 1" }
    }
}

