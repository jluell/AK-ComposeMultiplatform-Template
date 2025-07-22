package shared.presentation.ui.icon

import akcmptemplate.shared.presentation.generated.resources.Res
import akcmptemplate.shared.presentation.generated.resources.ic_arrow_back
import akcmptemplate.shared.presentation.generated.resources.ic_backspace
import akcmptemplate.shared.presentation.generated.resources.ic_cancel
import akcmptemplate.shared.presentation.generated.resources.ic_chevron_right
import akcmptemplate.shared.presentation.generated.resources.ic_coffee
import akcmptemplate.shared.presentation.generated.resources.ic_dark_mode
import akcmptemplate.shared.presentation.generated.resources.ic_delete
import akcmptemplate.shared.presentation.generated.resources.ic_info
import akcmptemplate.shared.presentation.generated.resources.ic_light_mode
import akcmptemplate.shared.presentation.generated.resources.ic_local_drink
import akcmptemplate.shared.presentation.generated.resources.ic_school
import akcmptemplate.shared.presentation.generated.resources.ic_wine_bar

object DsIcons {

    val info: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_info)
    val cancel: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_cancel)
    val arrowBack: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_arrow_back)
    val chevronRight: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_chevron_right)
    val lightMode: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_light_mode)
    val darkMode: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_dark_mode)
    val school: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_school)
    val coffee: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_coffee)
    val wineBar: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_wine_bar)
    val localDrink: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_local_drink)
    val delete: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_delete)
    val backspace: DsIconModel
        get() = DsIconModel.DrawableResource(Res.drawable.ic_backspace)
}
