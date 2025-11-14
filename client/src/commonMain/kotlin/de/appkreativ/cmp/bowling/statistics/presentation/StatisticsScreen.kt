package de.appkreativ.cmp.bowling.statistics.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun StatisticsScreen(navController: NavHostController) {
    val viewModel: StatisticsViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Statistics Screen - To be implemented")
        }
    }
}

class StatisticsViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = StatisticsMutableState()
}

class StatisticsMutableState {
    // State properties to be added
}

