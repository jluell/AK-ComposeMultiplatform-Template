package de.appkreativ.cmp.platform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import de.appkreativ.cmp.Application
import org.koin.dsl.module

actual fun NavGraphBuilder.platform(navController: NavHostController) {
}

actual val platform = module {
}