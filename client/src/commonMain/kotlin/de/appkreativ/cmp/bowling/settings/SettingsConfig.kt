package de.appkreativ.cmp.bowling.settings

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.settings.presentation.SettingsScreen
import de.appkreativ.cmp.bowling.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.settings(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.SettingsRoute> {
        SettingsScreen(navController = navController)
    }
}

val settings = module {
    viewModelOf(::SettingsViewModel)
}

