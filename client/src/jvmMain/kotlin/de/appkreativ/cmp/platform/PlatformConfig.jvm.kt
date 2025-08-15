package de.appkreativ.cmp.platform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.koin.dsl.module
import java.io.File

actual fun NavGraphBuilder.platform(navController: NavHostController) = Unit

actual val platform = module {
}