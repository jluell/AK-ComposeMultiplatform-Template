package shared.data.repository

import shared.core.util.Result
import shared.core.util.runCatchingResultSuspend
import shared.domain.model.Penalty
import shared.domain.model.PenaltyType
import shared.domain.repository.PenaltyRepository
import shared.data.mapper.toDomain
import shared.data.mapper.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import shared.data.database.BowlingDatabase

class PenaltyRepositoryImpl(
    private val database: BowlingDatabase
) : PenaltyRepository {
    
    override suspend fun getById(id: String): Result<Penalty> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = database.bowlingQueries.selectPenaltyById(id).executeAsOneOrNull()
                ?: throw IllegalArgumentException("Penalty not found: $id")
            entity.toDomain()
        }
    }
    
    override suspend fun getByEventId(eventId: String): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectPenaltiesByEvent(eventId).executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getByPlayerId(playerId: String): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectPenaltiesByPlayer(playerId).executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getByEventAndPlayer(eventId: String, playerId: String): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectPenaltiesByEventAndPlayer(eventId, playerId).executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getByType(type: PenaltyType): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectPenaltiesByType(type.name).executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getByYear(year: Int): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            // Get all events for the year, then get penalties
            val eventRepo = EventRepositoryImpl(database)
            val events = eventRepo.getByYear(year).fold(
                onSuccess = { it },
                onError = { throw it }
            )
            val eventIds = events.map { it.id }
            // Get all penalties and filter by event IDs
            getAll().fold(
                onSuccess = { penalties -> penalties.filter { it.eventId in eventIds } },
                onError = { throw it }
            )
        }
    }
    
    override suspend fun save(penalty: Penalty): Result<Penalty> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = penalty.toEntity()
            database.bowlingQueries.insertPenalty(
                id = entity.id,
                event_id = entity.event_id,
                player_id = entity.player_id,
                type = entity.type,
                amount = entity.amount,
                description = entity.description,
                created_at = entity.created_at
            )
            penalty
        }
    }
    
    override suspend fun delete(id: String): Result<Unit> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.deletePenalty(id)
        }
    }
    
    override suspend fun deleteByEventId(eventId: String): Result<Unit> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.deletePenaltiesByEvent(eventId)
        }
    }
    
    private suspend fun getAll(): Result<List<Penalty>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            // This would require a selectAll query, but we can work around it
            // by getting all events and then all penalties
            val eventRepo = EventRepositoryImpl(database)
            val events = eventRepo.getAll().fold(
                onSuccess = { it },
                onError = { throw it }
            )
            val allPenalties = mutableListOf<Penalty>()
            events.forEach { event ->
                val penalties = getByEventId(event.id).fold(
                    onSuccess = { it },
                    onError = { throw it }
                )
                allPenalties.addAll(penalties)
            }
            allPenalties
        }
    }
}

