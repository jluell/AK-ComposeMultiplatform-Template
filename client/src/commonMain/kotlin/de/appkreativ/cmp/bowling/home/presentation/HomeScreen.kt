package de.appkreativ.cmp.bowling.home.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Home Screen - To be implemented")
        }
    }
}

class HomeViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = HomeMutableState()
}

class HomeMutableState {
    // State properties to be added
}

