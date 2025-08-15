package de.appkreativ.cmp.platform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask

actual fun NavGraphBuilder.platform(navController: NavHostController) = Unit

actual val platform = module {
}