package shared.domain.usecase

import shared.domain.model.FortyShotScore
import shared.domain.model.PlayerRanking

/**
 * Use case for calculating best N scores.
 * 
 * Selects the best N scores from all scores for ranking purposes.
 */
object CalculateBestNScores {
    /**
     * Calculates best N scores for a player.
     * 
     * @param scores All scores for the player
     * @param n Number of best scores to select (e.g., 7)
     * @return List of best N scores, sorted descending
     */
    fun calculate(scores: List<FortyShotScore>, n: Int): List<Int> {
        require(n > 0) { "N must be positive" }
        return scores
            .map { it.totalScore }
            .sortedDescending()
            .take(n)
    }
    
    /**
     * Calculates the total of best N scores.
     * 
     * @param scores All scores for the player
     * @param n Number of best scores to select
     * @return Sum of best N scores
     */
    fun calculateTotal(scores: List<FortyShotScore>, n: Int): Int {
        return calculate(scores, n).sum()
    }
    
    /**
     * Creates a PlayerRanking from scores.
     * 
     * @param playerId Player ID
     * @param scores All scores for the player
     * @param n Number of best scores to select
     * @param rank Final rank (to be assigned separately)
     * @return PlayerRanking object
     */
    fun createRanking(
        playerId: String,
        scores: List<FortyShotScore>,
        n: Int,
        rank: Int
    ): PlayerRanking {
        val bestNScores = calculate(scores, n)
        val bestNTotal = bestNScores.sum()
        
        return PlayerRanking(
            playerId = playerId,
            bestNScores = bestNScores,
            bestNTotal = bestNTotal,
            rank = rank
        )
    }
}

