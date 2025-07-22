package de.appkreativ.cmp.theme.provide.presentation

import androidx.compose.runtime.Composable
import shared.presentation.viewmodel.provideViewModel
import shared.presentation.theme.ThemeProvider as SharedThemeProvider

@Composable
fun ThemeProvider(content: @Composable () -> Unit) {
    val viewModel: ThemeStatelessViewModel = provideViewModel()
    SharedThemeProvider(viewModel.state, content)
}