package com.rahulverlekar.ghoststories.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.domain.repository.GhostSightingRepository
import com.rahulverlekar.ghoststories.ui.intent.AddEditGhostSightingIntent
import com.rahulverlekar.ghoststories.ui.intent.AddEditUiEvent
import com.rahulverlekar.ghoststories.ui.intent.GhostSightingIntent
import com.rahulverlekar.ghoststories.ui.state.AddEditGhostSightingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditGhostSightingViewModel @Inject constructor(
    private val repository: GhostSightingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddEditGhostSightingState())
    val state = _state.asStateFlow()

    private val _uiEvent = MutableSharedFlow<AddEditUiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    fun onIntent(intent: AddEditGhostSightingIntent) {
        when (intent) {
            AddEditGhostSightingIntent.Cancel -> {
                viewModelScope.launch {
                    _uiEvent.emit(AddEditUiEvent.NavigateBack)
                }
            }

            is AddEditGhostSightingIntent.NameChanged -> {
                viewModelScope.launch {
                    _state.value = _state.value.copy(name = intent.name)
                }
            }

            is AddEditGhostSightingIntent.ScarinessChanged -> {
                _state.value = _state.value.copy(scariness = intent.scariness)
            }

            AddEditGhostSightingIntent.Save -> {
                viewModelScope.launch {
                    if(_state.value.name.isBlank()) {
                        _uiEvent.emit(AddEditUiEvent.ShowMessage("Name cannot be blank"))
                        return@launch
                    }
                    if (_state.value.isEditMode) {
                        repository.edit(
                            GhostSighting(
                                id = _state.value.id,
                                name = _state.value.name,
                                scariness = _state.value.scariness
                            )
                        )
                    }
                    else {
                        repository.add(
                            GhostSighting(
                                id = 0,
                                name = _state.value.name,
                                scariness = _state.value.scariness
                            )
                        )
                    }
                    _uiEvent.emit(AddEditUiEvent.Refresh)
                }
            }

            is AddEditGhostSightingIntent.GhostIdSelected -> {
                viewModelScope.launch {
                    val selected = repository.getSighting(intent.id)
                    selected?.let {
                        _state.value = _state.value.copy(
                            name = selected.name,
                            scariness = selected.scariness,
                            isEditMode = true,
                            id = selected.id
                        )
                    }
                }
            }
        }
    }

}