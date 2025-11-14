package de.appkreativ.cmp.bowling.penalties

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.penalties.presentation.PenaltiesScreen
import de.appkreativ.cmp.bowling.penalties.presentation.PenaltiesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.penalties(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.PenaltiesRoute> {
        PenaltiesScreen(navController = navController)
    }
}

val penalties = module {
    viewModelOf(::PenaltiesViewModel)
}

