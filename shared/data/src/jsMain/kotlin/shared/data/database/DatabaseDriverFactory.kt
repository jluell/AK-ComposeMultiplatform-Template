package shared.data.database

import app.cash.sqldelight.driver.sqljs.initSqlDriver
import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.await

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        return initSqlDriver(BowlingDatabase.Schema).await()
    }
}

