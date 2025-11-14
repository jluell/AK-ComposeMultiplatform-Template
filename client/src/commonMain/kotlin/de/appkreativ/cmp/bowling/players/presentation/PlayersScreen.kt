package de.appkreativ.cmp.bowling.players.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import de.appkreativ.cmp.getViewModel
import shared.presentation.ui.container.DsScaffold

@Composable
fun PlayersScreen(navController: NavHostController) {
    val viewModel: PlayersViewModel = getViewModel()
    val state = viewModel.state

    DsScaffold() { paddingValues ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text("Players Screen - To be implemented")
        }
    }
}

