package de.appkreativ.cmp.bowling.scoring

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.scoring.presentation.ScoringScreen
import de.appkreativ.cmp.bowling.scoring.presentation.ScoringViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.scoring(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.ScoringRoute> {
        ScoringScreen(navController = navController)
    }
}

val scoring = module {
    viewModelOf(::ScoringViewModel)
}

