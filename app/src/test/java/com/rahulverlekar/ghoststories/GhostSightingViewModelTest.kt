package com.rahulverlekar.ghoststories

import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.domain.repository.GhostSightingRepository
import com.rahulverlekar.ghoststories.ui.intent.GhostSightingIntent
import com.rahulverlekar.ghoststories.ui.viewmodel.GhostSightingViewModel
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GhostSightingViewModelTest {

    private lateinit var repository: GhostSightingRepository
    private lateinit var viewModel: GhostSightingViewModel


    @Before
    fun setup() {
        repository = FakeGhostSightingRepository()
    }

    @Test
    fun `repository returns empty list updates state correctly`() = runTest {
        coEvery { repository.getAllSighting() } returns emptyList()
        viewModel = GhostSightingViewModel(repository)

        viewModel.onIntent(GhostSightingIntent.LoadSightings)
        val state = viewModel.listState.value
        assertTrue(!state.sighting.isEmpty())
    }
}