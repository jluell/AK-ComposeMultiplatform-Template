package de.appkreativ.cmp.home.presentation

import de.appkreativ.cmp.platform.getPlatformName
import shared.presentation.viewmodel.BaseViewModel

class HomeViewModel() : BaseViewModel() {

    private val _state = HomeMutableState(platformName = getPlatformName())
    val state: HomeState = _state

    private class HomeMutableState(
        override val platformName: String
    ) : HomeState
}
