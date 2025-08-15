package de.appkreativ.cmp.app.presentation

import de.appkreativ.cmp.home.presentation.HomeRoute
import shared.presentation.theme.ThemeState
import shared.presentation.viewmodel.BaseViewModel

class AppViewModel(
    val state: AppState,
    val themeState: ThemeState
) : BaseViewModel() {

    override fun doBind() {
        withState {
            themeState.currentConfig = themeState.defaultConfig
            state.setStartDestination(HomeRoute)
        }
    }
}
