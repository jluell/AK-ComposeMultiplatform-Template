package shared.domain.repository

import shared.core.util.Result
import shared.domain.model.YearlySummary

/**
 * Repository interface for yearly summary operations.
 */
interface YearlySummaryRepository {
    suspend fun getById(id: String): Result<YearlySummary>
    suspend fun getByYear(year: Int): Result<YearlySummary?>
    suspend fun getAll(): Result<List<YearlySummary>>
    suspend fun save(summary: YearlySummary): Result<YearlySummary>
    suspend fun delete(id: String): Result<Unit>
    suspend fun deleteByYear(year: Int): Result<Unit>
}

