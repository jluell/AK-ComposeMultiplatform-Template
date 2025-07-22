package de.appkreativ.cmp.platform

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import de.appkreativ.cmp.common.data.source.database.DatabaseSource
import de.appkreativ.cmp.common.data.source.database.sqldelight.SqlDelightSource
import org.koin.dsl.module
import shared.data.source.settings.SettingsSource
import shared.data.source.settings.datastore.DataStoreSource
import java.io.File

actual fun NavGraphBuilder.platform(navController: NavHostController) = Unit

actual fun InitializerViewModelFactoryBuilder.platform() = Unit

actual val platform = module {
    single<DatabaseSource> {
        val dbName = "app.db"
        val driver = JdbcSqliteDriver("jdbc:sqlite:${dbName}")
        SqlDelightSource(driver)
    }
    single<SettingsSource> {
        val fileName = "app.preferences_pb"
        val file = File(System.getProperty("java.io.tmpdir"), fileName)
        DataStoreSource(file.absolutePath)
    }
}