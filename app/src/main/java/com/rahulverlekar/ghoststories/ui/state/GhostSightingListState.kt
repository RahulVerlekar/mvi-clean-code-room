package com.rahulverlekar.ghoststories.ui.state

import com.rahulverlekar.domain.models.GhostSighting

data class GhostSightingListState (
    val sighting: List<GhostSighting> = emptyList(),
    val isLoading: Boolean= false,
    val error: String? = null
)