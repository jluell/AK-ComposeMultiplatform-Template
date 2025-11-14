package shared.domain.model

import kotlin.time.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents a player in the bowling club.
 *
 * @property id Unique identifier for the player
 * @property name Player's name
 * @property joinDate Date when the player joined the club
 * @property isActive Whether the player is currently active
 * @property createdAt Timestamp when the player record was created
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class Player(
    val id: String,
    val name: String,
    val joinDate: LocalDate,
    val isActive: Boolean = true,
    val createdAt: Instant
)

