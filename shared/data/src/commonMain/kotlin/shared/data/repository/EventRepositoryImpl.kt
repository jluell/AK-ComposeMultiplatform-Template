package shared.data.repository

import shared.core.util.Result
import shared.core.util.runCatchingResultSuspend
import shared.domain.model.Event
import shared.domain.model.EventStatus
import shared.domain.repository.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import shared.data.database.BowlingDatabase
import shared.data.mapper.toDomain
import shared.data.mapper.toEntity

class EventRepositoryImpl(
    private val database: BowlingDatabase
) : EventRepository {
    
    override suspend fun getById(id: String): Result<Event> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = database.bowlingQueries.selectEventById(id).executeAsOneOrNull()
                ?: throw IllegalArgumentException("Event not found: $id")
            entity.toDomain()
        }
    }
    
    override suspend fun getAll(): Result<List<Event>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectAllEvents().executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun getByDate(date: LocalDate): Result<Event?> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectEventByDate(date.toString()).executeAsOneOrNull()?.toDomain()
        }
    }
    
    override suspend fun getByYear(year: Int): Result<List<Event>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            // Filter by year in memory since SQLite doesn't have easy year extraction
            getAll().fold(
                onSuccess = { events -> events.filter { it.date.year == year } },
                onError = { throw it }
            )
        }
    }
    
    override suspend fun getByStatus(status: EventStatus): Result<List<Event>> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.selectEventsByStatus(status.name).executeAsList().map { it.toDomain() }
        }
    }
    
    override suspend fun save(event: Event): Result<Event> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            val entity = event.toEntity()
            database.bowlingQueries.insertEvent(
                id = entity.id,
                date = entity.date,
                location = entity.location,
                status = entity.status,
                created_at = entity.created_at
            )
            event
        }
    }
    
    override suspend fun delete(id: String): Result<Unit> = runCatchingResultSuspend {
        withContext(Dispatchers.Main) {
            database.bowlingQueries.deleteEvent(id)
        }
    }
}

