package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.FortyShotScore

/**
 * Repository interface for 40-shot score operations.
 */
interface ScoreRepository {
    suspend fun getById(id: String): Result<FortyShotScore>
    suspend fun getByEventId(eventId: String): Result<List<FortyShotScore>>
    suspend fun getByPlayerId(playerId: String): Result<List<FortyShotScore>>
    suspend fun getByEventAndPlayer(eventId: String, playerId: String): Result<FortyShotScore?>
    suspend fun getByYear(year: Int): Result<List<FortyShotScore>>
    suspend fun save(score: FortyShotScore): Result<FortyShotScore>
    suspend fun delete(id: String): Result<Unit>
    suspend fun deleteByEventId(eventId: String): Result<Unit>
}

