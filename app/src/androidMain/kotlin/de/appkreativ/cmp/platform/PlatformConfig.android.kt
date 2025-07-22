package de.appkreativ.cmp.platform

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import de.appkreativ.cmp.Application
import de.appkreativ.cmp.common.data.source.database.DatabaseSource
import de.appkreativ.cmp.common.data.source.database.sqldelight.SqlDelightSource
import org.koin.dsl.module
import shared.data.source.settings.SettingsSource
import shared.data.source.settings.datastore.DataStoreSource
import de.appkreativ.cmp.common.data.source.database.sqldelight.AppDatabase as SqlDelightDatabase

actual fun NavGraphBuilder.platform(navController: NavHostController) {
}

actual fun InitializerViewModelFactoryBuilder.platform() {
}

actual val platform = module {
    single<DatabaseSource> {
        val dbName = "app.db"
        val context = Application.ref
        val driver = AndroidSqliteDriver(SqlDelightDatabase.Schema.synchronous(), context, dbName)
        SqlDelightSource(driver)
    }
    single<SettingsSource> {
        val fileName = "app.preferences_pb"
        val path = Application.ref.filesDir.resolve(fileName).absolutePath
        DataStoreSource(path)
    }
}