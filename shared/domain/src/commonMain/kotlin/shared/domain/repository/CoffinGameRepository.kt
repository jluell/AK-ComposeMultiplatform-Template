package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.CoffinGame

/**
 * Repository interface for coffin game operations.
 */
interface CoffinGameRepository {
    suspend fun getById(id: String): Result<CoffinGame>
    suspend fun getByEventId(eventId: String): Result<CoffinGame?>
    suspend fun getAll(): Result<List<CoffinGame>>
    suspend fun save(game: CoffinGame): Result<CoffinGame>
    suspend fun delete(id: String): Result<Unit>
    suspend fun deleteByEventId(eventId: String): Result<Unit>
}

