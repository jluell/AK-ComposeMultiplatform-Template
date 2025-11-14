package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.Event
import kotlinx.datetime.LocalDate

/**
 * Repository interface for event operations.
 */
interface EventRepository {
    suspend fun getById(id: String): Result<Event>
    suspend fun getAll(): Result<List<Event>>
    suspend fun getByDate(date: LocalDate): Result<Event?>
    suspend fun getByYear(year: Int): Result<List<Event>>
    suspend fun getByStatus(status: shared.domain.model.EventStatus): Result<List<Event>>
    suspend fun save(event: Event): Result<Event>
    suspend fun delete(id: String): Result<Unit>
}

