package com.rahulverlekar.ghoststories.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.domain.repository.GhostSightingRepository
import com.rahulverlekar.ghoststories.ui.intent.AddEditGhostSightingIntent
import com.rahulverlekar.ghoststories.ui.state.AddEditGhostSightingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGhostSightingViewModel @Inject constructor(
    private val repository: GhostSightingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditGhostSightingState())
    val state = _state.asStateFlow()

    fun onIntent(intent: AddEditGhostSightingIntent) {
        when (intent) {
            AddEditGhostSightingIntent.Cancel -> {

            }

            is AddEditGhostSightingIntent.NameChanged -> {
                _state.value = _state.value.copy(name = intent.name)
            }

            is AddEditGhostSightingIntent.ScarinessChanged -> {
                _state.value = _state.value.copy(scariness = intent.scariness)
            }

            AddEditGhostSightingIntent.Save -> {
                viewModelScope.launch {
                    repository.add(
                        GhostSighting(
                            id = 0,
                            name = _state.value.name,
                            scariness = _state.value.scariness
                        )
                    )
                }
            }
        }
    }

}