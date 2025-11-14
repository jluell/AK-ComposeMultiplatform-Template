package de.appkreativ.cmp.bowling.scoring.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun ScoringScreen(navController: NavHostController) {
    val viewModel: ScoringViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Scoring Screen - To be implemented")
        }
    }
}

class ScoringViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = ScoringMutableState()
}

class ScoringMutableState {
    // State properties to be added
}

