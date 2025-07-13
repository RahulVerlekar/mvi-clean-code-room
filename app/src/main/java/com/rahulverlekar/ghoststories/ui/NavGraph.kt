package com.rahulverlekar.ghoststories.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rahulverlekar.ghoststories.ui.screen.HomeScreen
import com.rahulverlekar.ghoststories.ui.viewmodel.GhostSightingViewModel

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "list") {
        composable("list") {
            val viewModel: GhostSightingViewModel = hiltViewModel()
            HomeScreen(viewModel, navController)
        }

        composable(
            "addEdit?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
                defaultValue = -1
            })
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt("id")?.takeIf { it != -1 }
//            AddEditGhostScreen(viewModel, navController, id)
        }
    }
}
