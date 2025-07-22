package de.appkreativ.cmp.platform

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import de.appkreativ.cmp.common.data.source.database.DatabaseSource
import de.appkreativ.cmp.common.data.source.database.sqldelight.SqlDelightSource
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask
import shared.data.source.settings.SettingsSource
import shared.data.source.settings.datastore.DataStoreSource
import de.appkreativ.cmp.common.data.source.database.sqldelight.AppDatabase as SqlDelightDatabase

actual fun NavGraphBuilder.platform(navController: NavHostController) = Unit

actual fun InitializerViewModelFactoryBuilder.platform() = Unit

actual val platform = module {
    single<DatabaseSource> {
        val dbName = "app.db"
        val driver = NativeSqliteDriver(SqlDelightDatabase.Schema.synchronous(), dbName)
        SqlDelightSource(driver)
    }
    single<SettingsSource> {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        val fileName = "app.preferences_pb"
        val directory = requireNotNull(documentDirectory).path
        val path = "${directory}/$fileName"
        DataStoreSource(path)
    }
}