package com.rahulverlekar.ghoststories.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulverlekar.domain.repository.GhostSightingRepository
import com.rahulverlekar.ghoststories.ui.intent.AddEditGhostSightingIntent
import com.rahulverlekar.ghoststories.ui.state.GhostSightingListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GhostSightingViewModel @Inject constructor(
    private val repository: GhostSightingRepository
): ViewModel() {

    private val _listState = MutableStateFlow(GhostSightingListState())
    val listState = _listState.asStateFlow()

    init {
        _listState.value = _listState.value.copy(isLoading = true)
        viewModelScope.launch {
            val data = repository.getAllSighting()
            _listState.value = _listState.value.copy(sighting = data, isLoading = false)
        }
    }
}