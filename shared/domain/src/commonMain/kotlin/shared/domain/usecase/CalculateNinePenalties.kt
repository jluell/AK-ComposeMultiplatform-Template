package shared.domain.usecase

import shared.domain.model.Penalty
import shared.domain.model.PenaltyType
import shared.domain.model.PenaltyAmount
import shared.domain.model.FortyShotScore
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Use case for calculating 9-pin penalties.
 * 
 * For every 9 hit by a player, all other players receive a €0.10 penalty.
 */
@OptIn(ExperimentalTime::class)
object CalculateNinePenalties {
    /**
     * Penalty amount per 9 hit: €0.10
     */
    const val PENALTY_PER_NINE = 0.10
    
    /**
     * Calculates 9-pin penalties for all players in an event.
     * 
     * @param scores All 40-shot scores for the event
     * @param eventId Event ID
     * @return List of penalty records for all players
     */
    fun calculateForEvent(
        scores: List<FortyShotScore>,
        eventId: String,
        createdAt: Instant
    ): List<Penalty> {
        // Calculate total 9s hit by all players
        val totalNines = scores.sumOf { it.ninesHit }
        
        // For each player, calculate penalty based on other players' 9s
        return scores.map { playerScore ->
            val ninesHitByOthers = totalNines - playerScore.ninesHit
            val penaltyAmount = ninesHitByOthers * PENALTY_PER_NINE
            
            if (penaltyAmount > 0) {
                Penalty(
                    id = "${eventId}_${playerScore.playerId}_nine_penalty",
                    eventId = eventId,
                    playerId = playerScore.playerId,
                    type = PenaltyType.NINE_PENALTY,
                    amount = PenaltyAmount(penaltyAmount),
                    description = "9-pin penalty: $ninesHitByOthers nines hit by other players",
                    createdAt = createdAt
                )
            } else {
                null
            }
        }.filterNotNull()
    }
    
    /**
     * Calculates the 9-pin penalty amount for a specific player.
     * 
     * @param scores All 40-shot scores for the event
     * @param playerId Player ID to calculate penalty for
     * @return Penalty amount in euros
     */
    fun calculateForPlayer(scores: List<FortyShotScore>, playerId: String): Double {
        val playerScore = scores.find { it.playerId == playerId }
        if (playerScore == null) return 0.0
        
        val totalNines = scores.sumOf { it.ninesHit }
        val ninesHitByOthers = totalNines - playerScore.ninesHit
        return ninesHitByOthers * PENALTY_PER_NINE
    }
}

