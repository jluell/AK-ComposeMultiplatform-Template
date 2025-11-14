package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.Player

/**
 * Repository interface for player operations.
 */
interface PlayerRepository {
    suspend fun getById(id: String): Result<Player>
    suspend fun getAll(): Result<List<Player>>
    suspend fun getActive(): Result<List<Player>>
    suspend fun save(player: Player): Result<Player>
    suspend fun delete(id: String): Result<Unit>
}

