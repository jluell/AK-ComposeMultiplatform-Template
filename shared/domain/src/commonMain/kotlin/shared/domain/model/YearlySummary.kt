package shared.domain.model

import kotlin.time.Instant
import kotlinx.serialization.Serializable
import kotlin.time.ExperimentalTime

/**
 * Represents the yearly summary with rankings and awards.
 *
 * @property id Unique identifier for the yearly summary
 * @property year Year this summary covers
 * @property playerRankings List of player rankings ordered by rank
 * @property overallWinner Player ID of the overall winner (highest best N total)
 * @property wetDogWinner Player ID of the "Wet Dog" winner (most penalties)
 * @property calculatedAt Timestamp when the summary was calculated
 */
@OptIn(ExperimentalTime::class)
@Serializable
data class YearlySummary(
    val id: String,
    val year: Int,
    val playerRankings: List<PlayerRanking>,
    val overallWinner: String,
    val wetDogWinner: String,
    val calculatedAt: Instant
) {
    init {
        require(year > 0) { "Year must be positive" }
        require(playerRankings.isNotEmpty()) { "Player rankings must not be empty" }
    }
}

