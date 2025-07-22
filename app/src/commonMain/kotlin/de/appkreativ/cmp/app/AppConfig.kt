package de.appkreativ.cmp.app

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.lifecycle.viewmodel.initializer
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import de.appkreativ.cmp.app.presentation.AppMutableState
import de.appkreativ.cmp.app.presentation.AppState
import de.appkreativ.cmp.app.presentation.AppViewModel
import de.appkreativ.cmp.common.common
import de.appkreativ.cmp.get
import de.appkreativ.cmp.home.home
import de.appkreativ.cmp.navigation.navigation
import de.appkreativ.cmp.platform.platform
import de.appkreativ.cmp.theme.theme
import org.koin.dsl.bind
import org.koin.dsl.module
import shared.presentation.ui.component.DsSnackbarState

fun NavGraphBuilder.app(navController: NavHostController) {
    platform(navController)
    common(navController)
    home(navController)
    navigation(navController)
    theme(navController)
}

fun InitializerViewModelFactoryBuilder.app() {
    initializer { AppViewModel(get()) }
    platform()
    common()
    home()
    navigation()
    theme()
}

val app = module {
    single { DsSnackbarState() }
    single { AppMutableState(get()) }.bind(AppState::class)
    includes(
        platform,
        common,
        home,
        navigation,
        theme,
    )
}