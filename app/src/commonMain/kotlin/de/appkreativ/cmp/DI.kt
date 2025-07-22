package de.appkreativ.cmp

import de.appkreativ.cmp.app.app
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf

val global = startKoin {
    printLogger()
    modules(app)
}

inline fun <reified T : Any> get(vararg parameters: Any?): T = global.koin.get<T> { parametersOf(*parameters) }