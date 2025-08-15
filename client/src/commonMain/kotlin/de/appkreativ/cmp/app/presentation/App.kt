package de.appkreativ.cmp.app.presentation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import de.appkreativ.cmp.app.app
import de.appkreativ.cmp.getViewModel
import shared.presentation.theme.ThemeProvider
import shared.presentation.ui.container.DsScaffold

@Composable
fun App() {
    val viewModel: AppViewModel = getViewModel()
    val navController = rememberNavController()
    val state = viewModel.state

    ThemeProvider(viewModel.themeState) {
        state.start?.let { startDestination ->
            DsScaffold { paddings ->
                NavHost(
                    modifier = Modifier.fillMaxSize().padding(paddings),
                    startDestination = startDestination,
                    contentAlignment = Alignment.Center,
                    enterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    builder = { app(navController) },
                    navController = navController
                )
            }
        }
    }
}
