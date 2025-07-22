package de.appkreativ.cmp.app.presentation

import de.appkreativ.cmp.home.presentation.HomeRoute
import shared.presentation.viewmodel.BaseViewModel

class AppViewModel(
    private val _state: AppMutableState
) : BaseViewModel() {

    val state: AppState = _state

    override fun doBind() {
        _state.startDestination = HomeRoute
    }
}