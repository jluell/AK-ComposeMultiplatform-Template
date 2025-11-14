import akcmptemplate.client.generated.resources.Res
import akcmptemplate.client.generated.resources.app_name
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import de.appkreativ.cmp.app.presentation.App
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = stringResource(Res.string.app_name),
        state = rememberWindowState(
            size = DpSize(480.dp, 640.dp)
        )
    ) {
        App()
    }
}