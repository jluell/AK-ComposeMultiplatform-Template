package shared.data.database

import app.cash.sqldelight.db.SqlDriver

/**
 * Factory interface for creating SQLDelight database drivers.
 * Platform-specific implementations will provide the actual driver.
 */
expect class DatabaseDriverFactory {
    suspend fun createDriver(): SqlDriver
}

