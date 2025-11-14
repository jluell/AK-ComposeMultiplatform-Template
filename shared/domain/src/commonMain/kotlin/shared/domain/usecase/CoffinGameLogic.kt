package shared.domain.usecase

import shared.domain.model.CoffinGame
import shared.domain.model.CoffinTurn
import shared.domain.model.CoffinElimination
import kotlin.random.Random

/**
 * Use case for coffin game logic.
 * 
 * Handles turn processing, elimination tracking, and ranking.
 */
object CoffinGameLogic {
    /**
     * Default coffin size (number of boards needed for elimination).
     */
    const val DEFAULT_COFFIN_SIZE = 6
    
    /**
     * Randomizes player order for the coffin game.
     * 
     * @param playerIds List of player IDs
     * @return Randomized list of player IDs
     */
    fun randomizePlayerOrder(playerIds: List<String>): List<String> {
        return playerIds.shuffled(Random.Default)
    }
    
    /**
     * Processes a turn and determines if a board should be awarded.
     * 
     * A player receives a board if they score worse than the previous player
     * OR worse than the next player.
     * 
     * @param currentScore Current player's score
     * @param previousScore Previous player's score (null if first turn)
     * @param nextScore Next player's score (null if last turn)
     * @return true if board should be awarded, false otherwise
     */
    fun shouldReceiveBoard(
        currentScore: Int,
        previousScore: Int?,
        nextScore: Int?
    ): Boolean {
        val worseThanPrevious = previousScore != null && currentScore < previousScore
        val worseThanNext = nextScore != null && currentScore < nextScore
        return worseThanPrevious || worseThanNext
    }
    
    /**
     * Calculates the number of boards a player has received.
     * 
     * @param turns All turns in the game
     * @param playerId Player ID
     * @return Total number of boards received
     */
    fun calculateBoards(turns: List<CoffinTurn>, playerId: String): Int {
        return turns
            .filter { it.playerId == playerId }
            .sumOf { it.boardsReceived }
    }
    
    /**
     * Checks if a player is eliminated (has complete coffin).
     * 
     * @param boards Number of boards received
     * @param coffinSize Size of coffin (default 6)
     * @return true if player is eliminated
     */
    fun isEliminated(boards: Int, coffinSize: Int = DEFAULT_COFFIN_SIZE): Boolean {
        return boards >= coffinSize
    }
    
    /**
     * Processes turns and determines eliminations and rankings.
     * 
     * @param playerOrder List of player IDs in order
     * @param turns List of all turns
     * @param coffinSize Size of coffin for elimination
     * @return List of eliminations with rankings
     */
    fun processEliminations(
        playerOrder: List<String>,
        turns: List<CoffinTurn>,
        coffinSize: Int = DEFAULT_COFFIN_SIZE
    ): List<CoffinElimination> {
        val eliminations = mutableListOf<CoffinElimination>()
        val eliminatedPlayers = mutableSetOf<String>()
        var currentRank = playerOrder.size
        
        // Process turns in order
        val turnsByTurnNumber = turns.sortedBy { it.turnNumber }
        
        for (turn in turnsByTurnNumber) {
            if (eliminatedPlayers.contains(turn.playerId)) {
                continue // Already eliminated
            }
            
            val boards = calculateBoards(turns, turn.playerId)
            if (isEliminated(boards, coffinSize)) {
                eliminatedPlayers.add(turn.playerId)
                eliminations.add(
                    CoffinElimination(
                        playerId = turn.playerId,
                        eliminationTurn = turn.turnNumber,
                        finalRank = currentRank
                    )
                )
                currentRank--
            }
        }
        
        // Last player standing gets rank 1
        val remainingPlayers = playerOrder.filter { it !in eliminatedPlayers }
        if (remainingPlayers.size == 1) {
            // Winner
            val winnerId = remainingPlayers.first()
            eliminations.add(
                CoffinElimination(
                    playerId = winnerId,
                    eliminationTurn = turnsByTurnNumber.lastOrNull()?.turnNumber ?: 0,
                    finalRank = 1
                )
            )
        }
        
        return eliminations.sortedByDescending { it.finalRank }
    }
}

