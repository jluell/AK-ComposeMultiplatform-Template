package de.appkreativ.cmp.platform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.koin.core.module.Module

expect fun NavGraphBuilder.platform(navController: NavHostController)

expect val platform: Module

/**
 * Returns the name of the current platform.
 *
 * @return Platform name (e.g., "Android", "iOS", "Desktop", "Web")
 */
expect fun getPlatformName(): String
