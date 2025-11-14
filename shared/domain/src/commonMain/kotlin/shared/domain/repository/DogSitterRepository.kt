package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.DogSitter

/**
 * Repository interface for dog sitter operations.
 */
interface DogSitterRepository {
    suspend fun getById(id: String): Result<DogSitter>
    suspend fun getCurrent(): Result<DogSitter?>
    suspend fun getByEventId(eventId: String): Result<DogSitter?>
    suspend fun getByPlayerId(playerId: String): Result<List<DogSitter>>
    suspend fun getAll(): Result<List<DogSitter>>
    suspend fun save(dogSitter: DogSitter): Result<DogSitter>
    suspend fun delete(id: String): Result<Unit>
    suspend fun setCurrent(eventId: String, playerId: String): Result<Unit>
}

