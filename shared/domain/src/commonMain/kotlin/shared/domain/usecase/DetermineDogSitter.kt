package shared.domain.usecase

import shared.domain.model.Penalty
import shared.domain.model.DogSitter
import kotlinx.datetime.LocalDate

/**
 * Use case for determining the dog sitter.
 * 
 * The dog sitter is the player with the most penalties in an event.
 * In case of a tie, the player with the most standard penalties wins.
 * If still tied, the first player alphabetically wins.
 */
object DetermineDogSitter {
    /**
     * Determines the dog sitter from penalties in an event.
     * 
     * @param penalties All penalties for the event
     * @param eventId Event ID
     * @param assignedDate Date of assignment
     * @param playerNames Map of player IDs to names for tie-breaking
     * @return DogSitter record, or null if no penalties exist
     */
    fun determine(
        penalties: List<Penalty>,
        eventId: String,
        assignedDate: LocalDate,
        playerNames: Map<String, String>
    ): DogSitter? {
        if (penalties.isEmpty()) return null
        
        // Group penalties by player
        val penaltiesByPlayer = penalties.groupBy { it.playerId }
        
        // Calculate total penalty amount per player
        val playerTotals = penaltiesByPlayer.mapValues { (_, playerPenalties) ->
            playerPenalties.sumOf { it.amount.value }
        }
        
        // Find maximum penalty total
        val maxTotal = playerTotals.values.maxOrNull() ?: return null
        
        // Find all players with the maximum total
        val candidates = playerTotals.filter { it.value == maxTotal }.keys.toList()
        
        // If tie, use standard penalty count
        val dogSitterId = if (candidates.size == 1) {
            candidates.first()
        } else {
            // Count standard penalties
            val standardPenaltyCounts = candidates.associateWith { playerId ->
                penaltiesByPlayer[playerId]?.count { it.type == shared.domain.model.PenaltyType.STANDARD } ?: 0
            }
            val maxStandardPenalties = standardPenaltyCounts.values.maxOrNull() ?: 0
            val standardCandidates = standardPenaltyCounts.filter { it.value == maxStandardPenalties }.keys.toList()
            
            if (standardCandidates.size == 1) {
                standardCandidates.first()
            } else {
                // Final tie-breaker: alphabetical by name
                standardCandidates.minByOrNull { playerNames[it] ?: it } ?: candidates.first()
            }
        }
        
        return DogSitter(
            id = "${eventId}_dog_sitter",
            eventId = eventId,
            playerId = dogSitterId,
            assignedDate = assignedDate,
            isCurrent = true
        )
    }
}

