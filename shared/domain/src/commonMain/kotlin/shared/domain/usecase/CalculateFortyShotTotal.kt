package shared.domain.usecase

import shared.domain.model.FortyShotScore
import shared.domain.model.RoundScore
import shared.domain.model.Lane
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Use case for calculating 40-shot total score.
 */
@OptIn(ExperimentalTime::class)
object CalculateFortyShotTotal {
    /**
     * Calculates the total score from all rounds.
     */
    fun calculate(rounds: List<RoundScore>): Int {
        require(rounds.size == 4) { "Must have exactly 4 rounds" }
        return rounds.sumOf { it.total }
    }
    
    /**
     * Counts the number of 9-pin hits across all rounds.
     */
    fun countNines(rounds: List<RoundScore>): Int {
        return rounds.sumOf { round -> round.throws.count { it == 9 } }
    }
    
    /**
     * Validates lane assignment (must have 2 left and 2 right).
     */
    fun validateLaneAssignment(rounds: List<RoundScore>): Boolean {
        val leftCount = rounds.count { it.lane == Lane.LEFT }
        val rightCount = rounds.count { it.lane == Lane.RIGHT }
        return leftCount == 2 && rightCount == 2
    }
    
    /**
     * Creates a FortyShotScore from rounds with calculated totals.
     */
    fun createScore(
        id: String,
        eventId: String,
        playerId: String,
        rounds: List<RoundScore>,
        createdAt: Instant
    ): FortyShotScore {
        val totalScore = calculate(rounds)
        val ninesHit = countNines(rounds)
        require(validateLaneAssignment(rounds)) {
            "Must have exactly 2 rounds on LEFT lane and 2 rounds on RIGHT lane"
        }
        
        return FortyShotScore(
            id = id,
            eventId = eventId,
            playerId = playerId,
            rounds = rounds,
            totalScore = totalScore,
            ninesHit = ninesHit,
            createdAt = createdAt
        )
    }
}

