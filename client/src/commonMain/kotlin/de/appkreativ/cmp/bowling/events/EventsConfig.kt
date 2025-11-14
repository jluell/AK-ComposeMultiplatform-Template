package de.appkreativ.cmp.bowling.events

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import de.appkreativ.cmp.bowling.events.presentation.EventsScreen
import de.appkreativ.cmp.bowling.events.presentation.EventsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

fun NavGraphBuilder.events(navController: NavHostController) {
    composable<de.appkreativ.cmp.bowling.navigation.EventsRoute> {
        EventsScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.EventDetailRoute> {
        // EventDetailScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.EventCreateRoute> {
        // EventCreateScreen(navController = navController)
    }
    composable<de.appkreativ.cmp.bowling.navigation.EventEditRoute> {
        // EventEditScreen(navController = navController)
    }
}

val events = module {
    viewModelOf(::EventsViewModel)
}

