import androidx.compose.ui.window.CanvasBasedWindow
import de.appkreativ.cmp.app.presentation.App
import org.jetbrains.skiko.wasm.onWasmReady

fun main() = onWasmReady {
    CanvasBasedWindow(canvasElementId = "appTarget") {
        App()
    }
}