package de.appkreativ.cmp.common

import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import org.koin.dsl.module
import shared.data.source.cache.BasicCacheSource
import shared.data.source.cache.CacheSource
import shared.data.source.config.BasicConfigSource
import shared.data.source.config.ConfigSource
import shared.data.source.http.HttpSource
import shared.data.source.paging.PagingSource
import shared.data.source.paging.multiplatform.MultiplatformPagingSource

fun NavGraphBuilder.common(navController: NavHostController) {}

fun InitializerViewModelFactoryBuilder.common() {}

val common = module {
    single { HttpSource() }
    single<CacheSource> { BasicCacheSource() }
    single<ConfigSource> { BasicConfigSource() }
    single<PagingSource> { MultiplatformPagingSource() }
}