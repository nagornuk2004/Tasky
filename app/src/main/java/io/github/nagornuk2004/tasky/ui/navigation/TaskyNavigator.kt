package io.github.nagornuk2004.tasky.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.nagornuk2004.tasky.ui.screens.FormScreen
import io.github.nagornuk2004.tasky.ui.screens.TasksScreen
import kotlinx.serialization.Serializable

@Serializable
object Tasks

@Serializable
object Form

@Composable
fun TaskyNavigator() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Tasks) {
        composable<Tasks> { TasksScreen() }
        composable<Form> { FormScreen() }
    }
}