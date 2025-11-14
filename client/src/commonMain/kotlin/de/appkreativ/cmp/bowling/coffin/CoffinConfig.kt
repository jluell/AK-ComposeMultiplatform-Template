package de.appkreativ.cmp.bowling.coffin

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.coffin.presentation.CoffinScreen
import de.appkreativ.cmp.bowling.coffin.presentation.CoffinViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.coffin(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.CoffinRoute> {
        CoffinScreen(navController = navController)
    }
}

val coffin = module {
    viewModelOf(::CoffinViewModel)
}

