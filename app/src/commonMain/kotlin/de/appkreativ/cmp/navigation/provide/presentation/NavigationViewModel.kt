package de.appkreativ.cmp.navigation.provide.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.serialization.generateHashCode
import de.appkreativ.cmp.common.presentation.navigation.NavigationItem
import de.appkreativ.cmp.common.presentation.navigation.NavigationState
import de.appkreativ.cmp.navigation.a.presentation.ARoute
import de.appkreativ.cmp.navigation.b.presentation.BRoute
import de.appkreativ.cmp.navigation.c.presentation.CRoute
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.serializer
import shared.presentation.ui.icon.DsIconModel
import shared.presentation.ui.icon.DsIcons
import shared.presentation.navigation.clearHistory
import shared.presentation.viewmodel.BaseViewModel

class NavigationViewModel : BaseViewModel() {

    private val _state = NavigationMutableState()
    val state: NavigationState = _state

    suspend fun onBind(navController: NavHostController) {
        val items = createItems(navController::clearHistory)
        val itemsById = items.associateBy { item -> item.id }

        withState {
            _state.items = items
            _state.visible = true
        }

        navController.currentBackStackEntryFlow
            .mapNotNull { entry -> entry.destination.id }
            .distinctUntilChanged()
            .map(itemsById::get)
            .collectLatest(_state::selected::set)
    }

    private fun createItems(onRoute: (route: Any) -> Unit) = listOf(
        createItem(
            route = ARoute,
            onRoute = onRoute,
            label = "Page A",
            activeIcon = DsIcons.wineBar,
            inactiveIcon = DsIcons.wineBar,
        ),
        createItem(
            route = BRoute,
            onRoute = onRoute,
            label = "Page B",
            activeIcon = DsIcons.localDrink,
            inactiveIcon = DsIcons.localDrink,
        ),
        createItem(
            route = CRoute,
            onRoute = onRoute,
            label = "Page C",
            activeIcon = DsIcons.coffee,
            inactiveIcon = DsIcons.coffee,
        )
    )

    private fun createItem(
        onRoute: (route: Any) -> Unit,
        inactiveIcon: DsIconModel,
        activeIcon: DsIconModel,
        label: String?,
        route: Any,
    ): NavigationItem {
        return NavigationItem(
            label = label,
            enabled = true,
            showLabel = false,
            activeIcon = activeIcon,
            inactiveIcon = inactiveIcon,
            onClick = { onRoute(route) },
            id = route.createItemId()
        )
    }

    private fun Any.createItemId(): Int = this::class.serializer().generateHashCode()

    private class NavigationMutableState : NavigationState {
        override var items: List<NavigationItem> by mutableStateOf(emptyList())
        override var selected: NavigationItem? by mutableStateOf(null)
        override var visible: Boolean? by mutableStateOf(null)
    }
}