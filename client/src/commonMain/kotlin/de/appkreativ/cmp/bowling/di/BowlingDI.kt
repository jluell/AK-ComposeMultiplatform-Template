package de.appkreativ.cmp.bowling.di

import org.koin.dsl.module
import shared.data.database.BowlingDatabase
import shared.data.database.DatabaseDriverFactory
import shared.data.repository.*
import shared.domain.repository.*

/**
 * Data module for dependency injection.
 */
val dataModule = module {
    single<DatabaseDriverFactory> {
        // Platform-specific factory will be provided
        throw NotImplementedError("DatabaseDriverFactory must be provided by platform")
    }

    single<BowlingDatabase> {
        // Database will be created with driver factory
        throw NotImplementedError("BowlingDatabase must be created with driver")
    }

    single<PlayerRepository> { PlayerRepositoryImpl(get()) }
    single<EventRepository> { EventRepositoryImpl(get()) }
    single<ScoreRepository> { ScoreRepositoryImpl(get()) }
    single<PenaltyRepository> { PenaltyRepositoryImpl(get()) }
    single<CoffinGameRepository> { CoffinGameRepositoryImpl(get()) }
    single<DogSitterRepository> { DogSitterRepositoryImpl(get()) }
    single<YearlySummaryRepository> { YearlySummaryRepositoryImpl(get()) }
}

/**
 * Bowling app module combining all feature modules.
 */
val bowlingModule = module {
    includes(
        dataModule,
        de.appkreativ.cmp.bowling.home.home,
        de.appkreativ.cmp.bowling.events.events,
        de.appkreativ.cmp.bowling.players.players,
        de.appkreativ.cmp.bowling.scoring.scoring,
        de.appkreativ.cmp.bowling.coffin.coffin,
        de.appkreativ.cmp.bowling.penalties.penalties,
        de.appkreativ.cmp.bowling.statistics.statistics,
        de.appkreativ.cmp.bowling.year_end.yearEnd,
        de.appkreativ.cmp.bowling.settings.settings
    )
}

