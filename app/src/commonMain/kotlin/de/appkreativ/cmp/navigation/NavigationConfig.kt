package de.appkreativ.cmp.navigation

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.initializer
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.navigation.a.presentation.ARoute
import de.appkreativ.cmp.navigation.a.presentation.AScreen
import de.appkreativ.cmp.navigation.a.presentation.AViewModel
import de.appkreativ.cmp.navigation.b.presentation.BRoute
import de.appkreativ.cmp.navigation.b.presentation.BScreen
import de.appkreativ.cmp.navigation.b.presentation.BViewModel
import de.appkreativ.cmp.navigation.c.presentation.CRoute
import de.appkreativ.cmp.navigation.c.presentation.CScreen
import de.appkreativ.cmp.navigation.c.presentation.CViewModel
import de.appkreativ.cmp.navigation.provide.presentation.NavigationViewModel
import org.koin.dsl.module

fun NavGraphBuilder.navigation(navController: NavHostController) {
    composable<ARoute> { AScreen() }
    composable<BRoute> { BScreen() }
    composable<CRoute> { CScreen() }
}

fun InitializerViewModelFactoryBuilder.navigation() {
    initializer { AViewModel() }
    initializer { BViewModel() }
    initializer { CViewModel() }
    initializer { NavigationViewModel() }
}

val navigation = module {}