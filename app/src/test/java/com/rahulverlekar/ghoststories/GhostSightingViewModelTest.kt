package com.rahulverlekar.ghoststories

import com.rahulverlekar.ghoststories.ui.intent.GhostSightingIntent
import com.rahulverlekar.ghoststories.ui.viewmodel.GhostSightingViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GhostSightingViewModelTest {

    private lateinit var repository: FakeGhostSightingRepository
    private lateinit var viewModel: GhostSightingViewModel

    @Before
    fun setup() {
        repository = FakeGhostSightingRepository()
        viewModel = GhostSightingViewModel(repository)
    }

    @Test
    fun `viewmodel model loads data when load intent is called`() = runTest {
        viewModel.onIntent(GhostSightingIntent.LoadSightings)
        val state = viewModel.listState
        assertTrue("Data loaded successfully", state.value.sighting.count() == repository.getAllSighting().count())
    }

    @Test
    fun `viewmodel is loading when there is delay while fetching`() = runTest {
        repository.simDelay = true
        val oldLoading = viewModel.listState.value.isLoading
        viewModel.onIntent(GhostSightingIntent.LoadSightings)
        val newLoading = viewModel.listState.value.isLoading
        assertTrue("Loading state changed", oldLoading != newLoading)
    }

}