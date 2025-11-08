package shared.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

/**
 * Creates and configures a Ktor HTTP client for multiplatform use.
 * 
 * This function sets up a client with:
 * - JSON serialization using kotlinx.serialization
 * - Logging for debugging
 * - Content negotiation
 * 
 * Platform-specific engines should be provided via the engine parameter:
 * - Android: Android.create()
 * - iOS: Darwin.create()
 * - JS: Js.create()
 * - JVM: Java.create()
 * 
 * @param engine HTTP client engine (platform-specific, must be provided)
 * @param block Optional configuration block for additional setup
 * @return Configured HttpClient instance
 */
expect fun createHttpClient(
    block: HttpClientConfig<*>.() -> Unit = {}
): HttpClient

/**
 * Common HTTP client configuration.
 */
fun HttpClientConfig<*>.configureJson() {
    val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }
    
    install(ContentNegotiation) {
        json(json)
    }
    
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println("HTTP Client: $message")
            }
        }
        level = LogLevel.INFO
    }
}

/**
 * Base URL configuration for API endpoints.
 * 
 * This should be configured per environment (development, staging, production).
 */
object ApiConfig {
    /**
     * Base URL for the API.
     * This should be set based on the build configuration or environment.
     */
    var baseUrl: String = "http://localhost:8080"
        private set
    
    /**
     * Sets the base URL for API calls.
     */
    fun setBaseUrl(url: String) {
        baseUrl = url
    }
}

