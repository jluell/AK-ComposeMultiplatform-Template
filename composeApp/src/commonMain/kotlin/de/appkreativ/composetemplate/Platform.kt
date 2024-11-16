package de.appkreativ.composetemplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform