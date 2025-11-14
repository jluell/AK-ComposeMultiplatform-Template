package shared.domain.usecase

import shared.domain.model.Penalty
import shared.domain.model.YearlySummary
import kotlin.time.ExperimentalTime

/**
 * Use case for determining awards.
 * 
 * Calculates the "Wet Dog" award (most penalties) and updates yearly summary.
 */
@OptIn(ExperimentalTime::class)
object DetermineAwards {
    /**
     * Determines the "Wet Dog" winner (player with most penalties).
     * 
     * @param penalties All penalties for the year, grouped by player ID
     * @return Player ID with most penalties, or null if no penalties
     */
    fun determineWetDog(penalties: Map<String, List<Penalty>>): String? {
        if (penalties.isEmpty()) return null
        
        val playerTotals = penalties.mapValues { (_, playerPenalties) ->
            playerPenalties.sumOf { it.amount.value }
        }
        
        return playerTotals.maxByOrNull { it.value }?.key
    }
    
    /**
     * Updates yearly summary with Wet Dog winner.
     * 
     * @param summary Yearly summary to update
     * @param penalties All penalties for the year, grouped by player ID
     * @return Updated yearly summary
     */
    fun updateWithAwards(
        summary: YearlySummary,
        penalties: Map<String, List<Penalty>>
    ): YearlySummary {
        val wetDogWinner = determineWetDog(penalties) ?: summary.overallWinner
        
        return summary.copy(wetDogWinner = wetDogWinner)
    }
}

