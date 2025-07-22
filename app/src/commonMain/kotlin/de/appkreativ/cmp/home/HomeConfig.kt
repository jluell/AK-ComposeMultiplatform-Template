package de.appkreativ.cmp.home

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.initializer
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.home.presentation.HomeRoute
import de.appkreativ.cmp.home.presentation.HomeScreen
import de.appkreativ.cmp.home.presentation.HomeViewModel
import org.koin.dsl.module

fun NavGraphBuilder.home(navController: NavHostController) {
    composable<HomeRoute> { HomeScreen() }
}

fun InitializerViewModelFactoryBuilder.home() {
    initializer { HomeViewModel() }
}

val home = module {}