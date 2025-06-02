package de.appkreativ.composetemplate

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import de.appkreativ.composetemplate.navigation.Screen
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val start = stringResource(Screen.Start.title)
        Button(
            onClick = { onNavigate(page = start) },
            content = {
                Text("Go to page 2")
            }
        )
    }
}

fun onNavigate(page: String) {

}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String?,
    val age: Int
)