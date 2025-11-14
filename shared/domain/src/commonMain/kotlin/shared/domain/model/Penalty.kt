package shared.domain.model

import kotlin.time.Instant
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline
import kotlin.time.ExperimentalTime

/**
 * Represents a penalty assigned to a player for an event.
 *
 * @property id Unique identifier for the penalty
 * @property eventId ID of the event this penalty belongs to
 * @property playerId ID of the player receiving the penalty
 * @property type Type of penalty
 * @property amount Penalty amount in euros
 * @property description Optional description of the penalty
 * @property createdAt Timestamp when the penalty was recorded
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class Penalty(
    val id: String,
    val eventId: String,
    val playerId: String,
    val type: PenaltyType,
    val amount: PenaltyAmount,
    val description: String? = null,
    val createdAt: Instant
)

/**
 * Wrapper for penalty amount to ensure proper decimal handling.
 * Uses Double for multiplatform compatibility. For precise calculations,
 * convert to platform-specific BigDecimal when needed.
 */
@Serializable
@JvmInline
value class PenaltyAmount(val value: Double) {
    init {
        require(value >= 0) { "Penalty amount must be non-negative" }
    }
}

