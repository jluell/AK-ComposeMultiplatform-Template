package shared.data.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import app.cash.sqldelight.db.SqlDriver
import shared.data.BowlingDatabase
import java.io.File

actual class DatabaseDriverFactory {
    actual suspend fun createDriver(): SqlDriver {
        val databasePath = File(System.getProperty("user.home"), "bowling.db")
        val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        BowlingDatabase.Schema.create(driver)
        return driver
    }
}

