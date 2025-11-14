package shared.data.repository

import shared.core.util.Result
import shared.core.util.runCatchingResultSuspend
import shared.domain.model.Player
import shared.domain.repository.PlayerRepository
import shared.data.mapper.toDomain
import shared.data.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import shared.data.database.BowlingDatabase

class PlayerRepositoryImpl(
    private val database: BowlingDatabase
) : PlayerRepository {
    
    override suspend fun getById(id: String): Result<Player> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = database.bowlingQueries.selectById(id).executeAsOneOrNull()
                ?: throw IllegalArgumentException("Player not found: $id")
            entity.toDomain()
        }
    }
    
    override suspend fun getAll(): Result<List<Player>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectAll().executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getActive(): Result<List<Player>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectActive().executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun save(player: Player): Result<Player> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = player.toEntity()
            database.bowlingQueries.insertPlayer(
                id = entity.id,
                name = entity.name,
                join_date = entity.join_date,
                is_active = entity.is_active,
                created_at = entity.created_at
            )
            player
        }
    }
    
    override suspend fun delete(id: String): Result<Unit> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.deletePlayer(id)
        }
    }
}

