package de.appkreativ.cmp.bowling.events.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun EventsScreen(navController: NavHostController) {
    val viewModel: EventsViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Events Screen - To be implemented")
        }
    }
}

class EventsViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = EventsMutableState()
}

class EventsMutableState {
    // State properties to be added
}

