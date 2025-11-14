package de.appkreativ.cmp.bowling.coffin.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun CoffinScreen(navController: NavHostController) {
    val viewModel: CoffinViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Coffin Game Screen - To be implemented")
        }
    }
}

class CoffinViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = CoffinMutableState()
}

class CoffinMutableState {
    // State properties to be added
}
