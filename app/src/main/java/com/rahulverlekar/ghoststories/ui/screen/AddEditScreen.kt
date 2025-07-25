package com.rahulverlekar.ghoststories.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rahulverlekar.ghoststories.ui.intent.AddEditGhostSightingIntent
import com.rahulverlekar.ghoststories.ui.intent.AddEditUiEvent
import com.rahulverlekar.ghoststories.ui.viewmodel.AddEditGhostSightingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGhostSightingScreen(
    viewModel: AddEditGhostSightingViewModel = hiltViewModel(),
    id: Int?,
    navController: NavController
) {

    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(id) {
        id?.let {
            viewModel.onIntent(AddEditGhostSightingIntent.GhostIdSelected(id))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AddEditUiEvent.Refresh -> {
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("shouldRefresh", true)
                    navController.popBackStack()
                }

                is AddEditUiEvent.NavigateBack -> {
                    navController.popBackStack()
                }

                is AddEditUiEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(title = { Text(text = "Add Ghost") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = {
                    viewModel.onIntent(
                        AddEditGhostSightingIntent.NameChanged(it)
                    )
                },
                label = {
                    Text(text = state.name)
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(text = "Scariness Level: ${state.scariness}")
            Slider(
                value = state.scariness.toFloat(),
                onValueChange = { viewModel.onIntent(AddEditGhostSightingIntent.ScarinessChanged(it.toInt())) },
                valueRange = 1f..10f,
                steps = 8,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(onClick = {
                    viewModel.onIntent(AddEditGhostSightingIntent.Cancel)
                }) {
                    Text("Cancel")
                }

                Button(onClick = {
                    viewModel.onIntent(AddEditGhostSightingIntent.Save)
                }) {
                    Text("Save")
                }
            }
        }
    }
}