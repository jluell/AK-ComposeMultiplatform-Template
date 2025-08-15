package de.appkreativ.cmp.app.presentation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
interface AppState {
    val start: Any?
    fun setStartDestination(start: Any)
}

class AppMutableState : AppState {
    override var start: Any? by mutableStateOf(null)
    override fun setStartDestination(start: Any) = this::start.set(start)
}
