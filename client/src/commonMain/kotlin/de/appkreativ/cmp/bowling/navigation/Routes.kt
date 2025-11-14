package de.appkreativ.cmp.bowling.navigation

import kotlinx.serialization.Serializable

/**
 * Navigation routes for the bowling app.
 */

@Serializable
object HomeRoute

@Serializable
object EventsRoute

@Serializable
data class EventDetailRoute(val eventId: String)

@Serializable
object EventCreateRoute

@Serializable
data class EventEditRoute(val eventId: String)

@Serializable
object PlayersRoute

@Serializable
data class PlayerDetailRoute(val playerId: String)

@Serializable
object PlayerCreateRoute

@Serializable
data class PlayerEditRoute(val playerId: String)

@Serializable
data class ScoringRoute(val eventId: String)

@Serializable
data class CoffinRoute(val eventId: String)

@Serializable
data class PenaltiesRoute(val eventId: String)

@Serializable
object StatisticsRoute

@Serializable
object YearEndRoute

@Serializable
data class YearEndDetailRoute(val year: Int)

@Serializable
object SettingsRoute

