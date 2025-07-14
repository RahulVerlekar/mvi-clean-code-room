package com.rahulverlekar.ghoststories.ui.intent

sealed class AddEditGhostSightingIntent {
    data class NameChanged(val name: String): AddEditGhostSightingIntent()
    data class ScarinessChanged(val scariness: Int): AddEditGhostSightingIntent()
    object Save: AddEditGhostSightingIntent()
    object Cancel: AddEditGhostSightingIntent()
}