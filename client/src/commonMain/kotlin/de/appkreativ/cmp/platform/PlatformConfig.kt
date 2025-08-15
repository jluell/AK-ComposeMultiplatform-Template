package de.appkreativ.cmp.platform

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.koin.core.module.Module

expect fun NavGraphBuilder.platform(navController: NavHostController)

expect val platform: Module
