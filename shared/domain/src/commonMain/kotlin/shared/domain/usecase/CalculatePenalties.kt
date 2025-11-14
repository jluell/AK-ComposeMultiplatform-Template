package shared.domain.usecase

import shared.domain.model.Penalty
import shared.domain.model.PenaltyType
import shared.domain.model.PenaltyAmount
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * Use case for calculating penalties.
 */
@OptIn(ExperimentalTime::class)
object CalculatePenalties {
    /**
     * Standard penalty amount: €1
     */
    const val STANDARD_PENALTY = 1.0
    
    /**
     * Absence penalty amount: €3
     */
    const val ABSENCE_PENALTY = 3.0
    
    /**
     * Forgot penalty object amount: €5
     */
    const val FORGOT_OBJECT_PENALTY = 5.0
    
    /**
     * 9-pin penalty per hit: €0.10
     */
    const val NINE_PENALTY_PER_HIT = 0.10
    
    /**
     * Calculates the total penalty amount for a list of penalties.
     */
    fun calculateTotal(penalties: List<Penalty>): Double {
        return penalties.sumOf { it.amount.value }
    }
    
    /**
     * Calculates the total penalty amount for a player in an event.
     */
    fun calculateEventTotal(penalties: List<Penalty>, playerId: String, eventId: String): Double {
        return penalties
            .filter { it.playerId == playerId && it.eventId == eventId }
            .sumOf { it.amount.value }
    }
    
    /**
     * Creates a standard penalty.
     */
    fun createStandardPenalty(
        id: String,
        eventId: String,
        playerId: String,
        description: String? = null,
        createdAt: Instant
    ): Penalty {
        return Penalty(
            id = id,
            eventId = eventId,
            playerId = playerId,
            type = PenaltyType.STANDARD,
            amount = PenaltyAmount(STANDARD_PENALTY),
            description = description,
            createdAt = createdAt
        )
    }
    
    /**
     * Creates an absence penalty.
     */
    fun createAbsencePenalty(
        id: String,
        eventId: String,
        playerId: String,
        createdAt: Instant
    ): Penalty {
        return Penalty(
            id = id,
            eventId = eventId,
            playerId = playerId,
            type = PenaltyType.ABSENCE,
            amount = PenaltyAmount(ABSENCE_PENALTY),
            description = "Absence from event",
            createdAt = createdAt
        )
    }
    
    /**
     * Creates a forgot penalty object penalty.
     */
    fun createForgotObjectPenalty(
        id: String,
        eventId: String,
        playerId: String,
        createdAt: Instant
    ): Penalty {
        return Penalty(
            id = id,
            eventId = eventId,
            playerId = playerId,
            type = PenaltyType.FORGOT_OBJECT,
            amount = PenaltyAmount(FORGOT_OBJECT_PENALTY),
            description = "Forgot to bring penalty object",
            createdAt = createdAt
        )
    }
}

