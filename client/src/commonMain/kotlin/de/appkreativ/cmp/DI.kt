package de.appkreativ.cmp

import androidx.compose.runtime.Composable
import de.appkreativ.cmp.app.app
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import shared.presentation.viewmodel.BaseViewModel

val koinApp = startKoin {
    printLogger()
    modules(app)
}

inline fun <reified T : Any> get(vararg parameters: Any?): T =
    koinApp.koin.get<T> { parametersOf(*parameters) }

@Composable
@OptIn(KoinInternalApi::class)
inline fun <reified T : BaseViewModel> getViewModel(key: String? = null): T {
    val viewModel = koinViewModel<T>(key = key, scope = koinApp.koin.scopeRegistry.rootScope)
    viewModel.bind()
    return viewModel
}
