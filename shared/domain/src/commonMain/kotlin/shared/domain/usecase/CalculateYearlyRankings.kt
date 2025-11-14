package shared.domain.usecase

import shared.domain.model.FortyShotScore
import shared.domain.model.PlayerRanking
import shared.domain.model.YearlySummary
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Use case for calculating yearly rankings.
 * 
 * Calculates rankings based on best N scores and determines awards.
 */
@OptIn(ExperimentalTime::class)
object CalculateYearlyRankings {
    /**
     * Calculates yearly rankings for all players.
     * 
     * @param scores All scores for the year, grouped by player ID
     * @param n Number of best scores to use (e.g., 7)
     * @param year Year being calculated
     * @param createdAt Timestamp for the summary
     * @return YearlySummary with rankings and awards
     */
    fun calculate(
        scores: Map<String, List<FortyShotScore>>,
        n: Int,
        year: Int,
        createdAt: Instant
    ): YearlySummary {
        require(n > 0) { "N must be positive" }
        require(scores.isNotEmpty()) { "Scores must not be empty" }
        
        // Calculate rankings for each player
        val rankings = scores.map { (playerId, playerScores) ->
            CalculateBestNScores.createRanking(
                playerId = playerId,
                scores = playerScores,
                n = n,
                rank = 0 // Will be assigned below
            )
        }
        
        // Sort by best N total (descending) and assign ranks
        val sortedRankings = rankings.sortedByDescending { it.bestNTotal }
        val rankedList = mutableListOf<PlayerRanking>()
        var currentRank = 1
        
        sortedRankings.forEachIndexed { index, ranking ->
            // If this score is different from previous, update rank
            if (index > 0 && ranking.bestNTotal < rankedList[index - 1].bestNTotal) {
                currentRank = index + 1
            }
            
            rankedList.add(
                ranking.copy(rank = currentRank)
            )
        }
        
        // Determine overall winner (rank 1)
        val overallWinner = rankedList.firstOrNull()?.playerId
            ?: throw IllegalArgumentException("No players found")
        
        // Note: Wet Dog winner requires penalty data, will be calculated separately
        val wetDogWinner = overallWinner // Placeholder, will be set by CalculateAwards
        
        return YearlySummary(
            id = "yearly_summary_$year",
            year = year,
            playerRankings = rankedList,
            overallWinner = overallWinner,
            wetDogWinner = wetDogWinner,
            calculatedAt = createdAt
        )
    }
}

