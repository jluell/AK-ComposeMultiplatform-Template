package de.appkreativ.cmp.theme

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.initializer
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import de.appkreativ.cmp.get
import de.appkreativ.cmp.theme.provide.presentation.ThemeStatelessViewModel
import org.koin.dsl.module
import shared.presentation.navigation.back
import shared.presentation.theme.DefaultThemeState
import shared.presentation.theme.ThemeConfig
import shared.presentation.theme.ThemeState
import shared.presentation.ui.theme.DsThemes

fun NavGraphBuilder.theme(navController: NavHostController) {
}

fun InitializerViewModelFactoryBuilder.theme() {
    initializer { ThemeStatelessViewModel(get()) }
}

val theme = module {
    single<ThemeState> {
        DefaultThemeState(
            defaultConfig = ThemeConfig(
                defaultTheme = DsThemes.Light,
                lightTheme = DsThemes.Light,
                darkTheme = DsThemes.Dark,
            )
        )
    }
}