package de.appkreativ.cmp.bowling.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.home.presentation.HomeScreen
import de.appkreativ.cmp.bowling.home.presentation.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.home(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.HomeRoute> {
        HomeScreen(navController = navController)
    }
}

val home = module {
    viewModelOf(::HomeViewModel)
}

