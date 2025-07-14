package com.rahulverlekar.ghoststories.ui.intent

sealed class AddEditGhostSightingIntent {
    data class NameChanged(val name: String): AddEditGhostSightingIntent()
    data class ScarinessChanged(val scariness: Int): AddEditGhostSightingIntent()
    data class GhostIdSelected(val id: Int): AddEditGhostSightingIntent()
    object Save: AddEditGhostSightingIntent()
    object Cancel: AddEditGhostSightingIntent()
}

sealed class AddEditUiEvent {
    object NavigateBack: AddEditUiEvent()
    object Refresh: AddEditUiEvent()
    data class ShowMessage(val message: String): AddEditUiEvent()
}