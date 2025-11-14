package de.appkreativ.cmp.bowling.year_end.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun YearEndScreen(navController: NavHostController) {
    val viewModel: YearEndViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Year-End Screen - To be implemented")
        }
    }
}

class YearEndViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = YearEndMutableState()
}

class YearEndMutableState {
    // State properties to be added
}

