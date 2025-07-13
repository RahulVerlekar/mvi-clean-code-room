package com.rahulverlekar.ghoststories.ui.intent

sealed class GhostSightingIntent {
    object LoadSightings : GhostSightingIntent()
    data class AddSighting(val name: String, val scariness: Int) : GhostSightingIntent()
    data class EditSighting(val id: Int, val name: String, val scariness: Int) : GhostSightingIntent()
}

