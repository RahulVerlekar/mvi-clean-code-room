package com.rahulverlekar.ghoststories.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.rahulverlekar.domain.repository.GhostSightingRepository
import com.rahulverlekar.ghoststories.ui.state.GhostSightingListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GhostSightingViewModel @Inject constructor(
    val repository: GhostSightingRepository
): ViewModel() {

    private val _listState = MutableStateFlow(GhostSightingListState())
    val listState = _listState.asStateFlow()

}