package de.appkreativ.cmp.bowling.players

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.players.presentation.PlayersScreen
import de.appkreativ.cmp.bowling.players.presentation.PlayersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.players(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.PlayersRoute> {
        PlayersScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.PlayerDetailRoute> {
        // PlayerDetailScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.PlayerCreateRoute> {
        // PlayerCreateScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.PlayerEditRoute> {
        // PlayerEditScreen(navController = navController)
    }
}

val players = module {
    viewModelOf(::PlayersViewModel)
}

