package com.rahulverlekar.ghoststories.ui.state

data class AddEditGhostSightingState (
    val name: String = "",
    val scariness: Int = 0,
    val isEditMode: Boolean = false,
    val id: Int = 0
)
