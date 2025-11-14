package de.appkreativ.cmp.bowling.settings.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun SettingsScreen(navController: NavHostController) {
    val viewModel: SettingsViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Settings Screen - To be implemented")
        }
    }
}

class SettingsViewModel : shared.presentation.viewmodel.BaseViewModel() {
    val state = SettingsMutableState()
}

class SettingsMutableState {
    // State properties to be added
}

