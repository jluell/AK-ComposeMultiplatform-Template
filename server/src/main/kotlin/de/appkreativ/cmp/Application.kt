package de.appkreativ.cmp

import de.appkreativ.cmp.plugins.configureCors
import de.appkreativ.cmp.plugins.configureRouting
import de.appkreativ.cmp.plugins.configureSerialization
import de.appkreativ.cmp.plugins.configureSinglePageApplication
import io.ktor.server.application.Application

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureSinglePageApplication()
    configureSerialization()
    configureRouting()
    configureCors()
}