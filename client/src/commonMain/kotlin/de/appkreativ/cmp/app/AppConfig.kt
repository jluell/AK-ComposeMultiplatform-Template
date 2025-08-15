package de.appkreativ.cmp.app

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import de.appkreativ.cmp.app.presentation.AppMutableState
import de.appkreativ.cmp.app.presentation.AppState
import de.appkreativ.cmp.app.presentation.AppViewModel
import de.appkreativ.cmp.common.common
import de.appkreativ.cmp.home.home
import de.appkreativ.cmp.platform.platform
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module
import shared.presentation.theme.DefaultThemeState
import shared.presentation.theme.ThemeConfig
import shared.presentation.theme.ThemeState
import shared.presentation.ui.theme.DsThemes

fun NavGraphBuilder.app(navController: NavHostController) {
    platform(navController)
    common(navController)
    home(navController)
}

val app = module {
    includes(
        platform,
        common,
        home
    )
    viewModelOf(::AppViewModel)
    singleOf(::AppMutableState).bind<AppState>()
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
