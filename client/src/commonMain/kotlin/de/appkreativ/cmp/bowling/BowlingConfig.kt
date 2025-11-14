package de.appkreativ.cmp.bowling

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import de.appkreativ.cmp.bowling.coffin.coffin
import de.appkreativ.cmp.bowling.events.events
import de.appkreativ.cmp.bowling.home.home
import de.appkreativ.cmp.bowling.penalties.penalties
import de.appkreativ.cmp.bowling.players.players
import de.appkreativ.cmp.bowling.scoring.scoring
import de.appkreativ.cmp.bowling.settings.settings
import de.appkreativ.cmp.bowling.statistics.statistics
import de.appkreativ.cmp.bowling.year_end.yearEnd

/**
 * Main navigation graph for bowling app features.
 */
fun NavGraphBuilder.bowling(navController: NavHostController) {
    home(navController)
    events(navController)
    players(navController)
    scoring(navController)
    coffin(navController)
    penalties(navController)
    statistics(navController)
    yearEnd(navController)
    settings(navController)
}

