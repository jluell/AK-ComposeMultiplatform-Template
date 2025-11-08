import androidx.compose.ui.window.ComposeViewport
import de.appkreativ.cmp.app.presentation.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() = onWasmReady {
    ComposeViewport(content = {
        App()
    })
}