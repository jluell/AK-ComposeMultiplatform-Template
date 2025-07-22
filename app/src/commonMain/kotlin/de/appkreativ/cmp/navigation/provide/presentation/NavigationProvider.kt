package de.appkreativ.cmp.navigation.provide.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import de.appkreativ.cmp.common.presentation.navigation.AdaptiveProvider
import shared.presentation.viewmodel.provideViewModel

@Composable
fun NavigationProvider(
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val viewModel: NavigationViewModel = provideViewModel()
    LaunchedEffect(navController) { viewModel.onBind(navController) }

    AdaptiveProvider(viewModel.state, content)
}