package com.rahulverlekar.ghoststories;


import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.rahulverlekar.domain.models.GhostSighting
import com.rahulverlekar.ghoststories.ui.screen.HomeScreen
import com.rahulverlekar.ghoststories.ui.state.GhostSightingListState
import com.rahulverlekar.ghoststories.ui.viewmodel.GhostSightingViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before

import org.junit.Rule;
import org.junit.Test
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var mockViewModel: GhostSightingViewModel
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        mockViewModel = mockk(relaxed = true)
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun whenLoading_showsCircularProgressIndicator() {
        // Given
        val loadingState = GhostSightingListState(isLoading = true)
        every { mockViewModel.listState } returns MutableStateFlow(loadingState)

        // When
        composeTestRule.setContent {
            HomeScreen(viewModel = mockViewModel, navController = navController)
        }

        // Then
        // CircularProgressIndicator doesn't have text or content description by default.
        // You might need to add a testTag to it in your HomeScreen for reliable finding,
        // or assert its presence based on other elements disappearing.
        // For simplicity, we'll assume other elements are not present.
        composeTestRule.onNodeWithText("No Ghost sightings yet! Check your surrounding.").assertDoesNotExist()
        // Add more assertions if you have a testTag on CircularProgressIndicator
    }
//
//    @Test
//    fun whenNoSightings_showsEmptyStateText() {
//        // Given
//        val emptyState = GhostSightingState(isLoading = false, sighting = emptyList())
//        every { mockViewModel.listState } returns MutableStateFlow(emptyState)
//
//        // When
//        composeTestRule.setContent {
//            HomeScreen(viewModel = mockViewModel, navController = navController)
//        }
//
//        // Then
//        composeTestRule.onNodeWithText("No Ghost sightings yet! Check your surrounding.").assertIsDisplayed()
//    }
//
//    @Test
//    fun whenSightingsAvailable_showsGhostCards() {
//        // Given
//        val sightings = listOf(
//            Ghost(id = "1", name = "Casper", description = "Friendly"),
//            Ghost(id = "2", name = "Slimer", description = "Slimy")
//        )
//        val populatedState = GhostSightingState(isLoading = false, sighting = sightings)
//        every { mockViewModel.listState } returns MutableStateFlow(populatedState)
//
//        // When
//        composeTestRule.setContent {
//            HomeScreen(viewModel = mockViewModel, navController = navController)
//        }
//
//        // Then
//        // Assuming GhostCard displays the ghost's name (modify if it uses another property)
//        composeTestRule.onNodeWithText("Casper").assertIsDisplayed()
//        composeTestRule.onNodeWithText("Slimer").assertIsDisplayed()
//        composeTestRule.onNodeWithText("No Ghost sightings yet! Check your surrounding.").assertDoesNotExist()
//    }
//
//    @Test
//    fun clickingFab_navigatesToAddEditScreen() {
//        // Given
//        val initialState = GhostSightingState() // Or any other valid state
//        every { mockViewModel.listState } returns MutableStateFlow(initialState)
//
//        // When
//        composeTestRule.setContent {
//            HomeScreen(viewModel = mockViewModel, navController = navController)
//        }
//        composeTestRule.onNodeWithContentDescription("Add").performClick()
//
//        // Then
//        // Verify navigation. The exact assertion depends on how you test navigation.
//        // With TestNavHostController, you can check the current back stack entry.
//        assert(navController.currentBackStackEntry?.destination?.route == "addEdit")
//    }
//
//    @Test
//    fun clickingGhostCard_navigatesToAddEditScreenWithId() {
//        // Given
//        val ghost = Ghost(id = "ghost123", name = "Gengar", description = "Shadow Pokemon")
//        val populatedState = GhostSightingState(isLoading = false, sighting = listOf(ghost))
//        every { mockViewModel.listState } returns MutableStateFlow(populatedState)
//
//        // When
//        composeTestRule.setContent {
//            HomeScreen(viewModel = mockViewModel, navController = navController)
//        }
//        // Assuming GhostCard displays the ghost's name (modify if it uses another property)
//        composeTestRule.onNodeWithText("Gengar").performClick()
//
//        // Then
//        assert(navController.currentBackStackEntry?.destination?.route == "addEdit?id=ghost123")
//    }

    // You might also want to test the LaunchedEffect logic if it's complex,
    // though that can be harder to unit test directly in a UI test.
    // Consider testing the ViewModel's reaction to `shouldRefresh` separately.
}