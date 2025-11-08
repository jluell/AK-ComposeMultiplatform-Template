package shared.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.android.Android

/**
 * Android implementation of HTTP client factory.
 */
actual fun createHttpClient(
    block: HttpClientConfig<*>.() -> Unit
): HttpClient {
    return HttpClient(Android) {
        configureJson()
        block()
    }
}

