package de.appkreativ.composetemplate.navigation

import ak_composemultiplatform_template.composeapp.generated.resources.Res
import ak_composemultiplatform_template.composeapp.generated.resources.menu_settings
import ak_composemultiplatform_template.composeapp.generated.resources.menu_start
import ak_composemultiplatform_template.composeapp.generated.resources.menu_terms
import org.jetbrains.compose.resources.StringResource

/**
 * enum values that represent the screens in the app
 */
enum class Screen(val title: StringResource) {
    Start(title = Res.string.menu_start),
    Terms(title = Res.string.menu_terms),
    Settings(title = Res.string.menu_settings),
}