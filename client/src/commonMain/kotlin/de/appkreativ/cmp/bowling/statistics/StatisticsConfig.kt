package de.appkreativ.cmp.bowling.statistics

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.statistics.presentation.StatisticsScreen
import de.appkreativ.cmp.bowling.statistics.presentation.StatisticsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.statistics(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.StatisticsRoute> {
        StatisticsScreen(navController = navController)
    }
}

val statistics = module {
    viewModelOf(::StatisticsViewModel)
}

