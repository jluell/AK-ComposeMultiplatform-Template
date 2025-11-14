package shared.domain.model

import kotlinx.serialization.Serializable

/**
 * Represents the type of penalty.
 */
@Serializable
enum class PenaltyType {
    STANDARD,        // €1
    ABSENCE,         // €3
    FORGOT_OBJECT,   // €5
    NINE_PENALTY     // €0.10 per 9 hit by other players
}

