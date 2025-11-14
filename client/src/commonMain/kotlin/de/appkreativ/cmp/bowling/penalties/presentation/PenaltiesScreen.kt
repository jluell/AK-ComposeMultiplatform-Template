package de.appkreativ.cmp.bowling.penalties.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun PenaltiesScreen(navController: NavHostController) {
    val viewModel: PenaltiesViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Penalties Screen - To be implemented")
        }
    }
}

class PenaltiesViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = PenaltiesMutableState()
}

class PenaltiesMutableState {
    // State properties to be added
}

