package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.Penalty

/**
 * Repository interface for penalty operations.
 */
interface PenaltyRepository {
    suspend fun getById(id: String): Result<Penalty>
    suspend fun getByEventId(eventId: String): Result<List<Penalty>>
    suspend fun getByPlayerId(playerId: String): Result<List<Penalty>>
    suspend fun getByEventAndPlayer(eventId: String, playerId: String): Result<List<Penalty>>
    suspend fun getByType(type: shared.domain.model.PenaltyType): Result<List<Penalty>>
    suspend fun getByYear(year: Int): Result<List<Penalty>>
    suspend fun save(penalty: Penalty): Result<Penalty>
    suspend fun delete(id: String): Result<Unit>
    suspend fun deleteByEventId(eventId: String): Result<Unit>
}

