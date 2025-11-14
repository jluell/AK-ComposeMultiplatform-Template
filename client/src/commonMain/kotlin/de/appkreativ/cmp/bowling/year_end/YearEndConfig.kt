package de.appkreativ.cmp.bowling.year_end

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.year_end.presentation.YearEndScreen
import de.appkreativ.cmp.bowling.year_end.presentation.YearEndViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.yearEnd(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.YearEndRoute> {
        YearEndScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.YearEndDetailRoute> {
        // YearEndDetailScreen(navController = navController)
    }
}

val yearEnd = module {
    viewModelOf(::YearEndViewModel)
}

