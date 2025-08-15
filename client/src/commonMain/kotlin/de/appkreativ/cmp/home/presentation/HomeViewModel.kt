package de.appkreativ.cmp.home.presentation

import shared.presentation.viewmodel.BaseViewModel

class HomeViewModel() : BaseViewModel() {

    private val _state = HomeMutableState()
    val state: HomeState = _state

    private class HomeMutableState() : HomeState
}
